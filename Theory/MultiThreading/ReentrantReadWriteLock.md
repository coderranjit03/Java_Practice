
---

# What is a Read–Write lock?

* A **Read–Write lock** gives you **two different locks** for the same shared resource:

  * **Read lock**: many threads can hold it **at the same time** (concurrent reads are safe).
  * **Write lock**: **exclusive**; only one thread can hold it, and **no readers** can hold the read lock while a writer is active.

**Why?** In real apps we often have **read-heavy** workloads (lots of reads, occasional writes). Allowing multiple readers in parallel boosts throughput compared to a single exclusive lock.

---

# The interface

```java
interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}
```

Java’s standard implementation is **`java.util.concurrent.locks.ReentrantReadWriteLock`**.

---

# Core behaviors (in simple words)

* **Many readers** can go in together if **no writer** is active or waiting (non-fair mode may allow some “barging”; details below).
* **Writer is exclusive**: when a writer holds the write lock, **no one else** (no readers, no other writers) can enter.
* **Lock is reentrant**: the same thread can acquire the same lock repeatedly (useful for nested calls).
* **You must always unlock in `finally`**.

---

# When should you use it?

Use **ReadWriteLock** when:

* Reads massively outnumber writes (e.g., 95% reads).
* Reads are long enough that letting them proceed together actually saves time.
* The data is **logically immutable** during read sections.

Avoid it when:

* Writes are frequent (then it behaves like a normal exclusive lock but with more overhead).
* Critical sections are extremely short (overhead may exceed benefits).
* You need condition waits on the read side (read lock doesn’t support `Condition`).

---

# Quick example: shared cache (read-heavy)

```java
import java.util.*;
import java.util.concurrent.locks.*;

public class ProductCache {
    private final Map<String, String> cache = new HashMap<>();
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock(); // default: unfair
    private final Lock r = rw.readLock();
    private final Lock w = rw.writeLock();

    public String get(String key) {
        r.lock();
        try {
            return cache.get(key); // many threads can do this in parallel
        } finally {
            r.unlock();
        }
    }

    public void put(String key, String value) {
        w.lock();
        try {
            cache.put(key, value); // only one writer at a time, and no readers
        } finally {
            w.unlock();
        }
    }
}
```

---

# Double-check pattern with **safe upgrade strategy**

**Important:** You **cannot upgrade** from read→write while holding the read lock (you’ll deadlock). The safe pattern is: release read, acquire write, verify again, update, and *optionally* **downgrade** (write→read) if needed.

```java
public String getOrLoad(String key) {
    // 1) First, optimistic read
    r.lock();
    try {
        String v = cache.get(key);
        if (v != null) return v;
    } finally {
        r.unlock();
    }

    // 2) Escalate safely: acquire write lock
    w.lock();
    try {
        // Check again in case someone else already filled it
        String v = cache.get(key);
        if (v == null) {
            v = loadFromDatabase(key);
            cache.put(key, v);
        }

        // 3) Optional: **downgrade** to read lock (hold read, then release write)
        r.lock();
        try {
            return v; // continue reading under read lock if you need to hold a lock
        } finally {
            r.unlock();
        }
    } finally {
        w.unlock();
    }
}
```

**Downgrading** (write → read) is allowed: acquire **read** while still holding **write**, then release **write**.
**Upgrading** (read → write) while still holding **read** is **not supported**—release read first.

---

# ReentrantReadWriteLock: key methods & knobs

### Constructing

```java
// Unfair (default): higher throughput, some barging allowed
ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();

// Fair: first-come-first-served (FIFO), helps avoid starvation but a bit slower
ReentrantReadWriteLock rw2 = new ReentrantReadWriteLock(true);
```

### Getting the locks

```java
Lock read  = rw.readLock();
Lock write = rw.writeLock();
```

### Lock operations (on both read & write unless noted)

* `lock()` – block until acquired.
* `lockInterruptibly()` – like lock(), but can be interrupted while waiting.
* `tryLock()` – try immediately; return `false` if not available (don’t block).
* `tryLock(time, unit)` – wait up to timeout.
* `unlock()` – release (always in `finally`).

### Introspection helpers (on the `ReentrantReadWriteLock` itself)

* `int getReadLockCount()` – how many holds on read lock (total).
* `boolean isWriteLocked()` – is write lock currently held?
* `int getWriteHoldCount()` – reentrancy depth for current thread on write lock.
* `int getReadHoldCount()` – reentrancy depth for current thread on read lock.
* Queue/Wait info: `hasQueuedThreads()`, `getQueueLength()`, etc.

### Conditions

* **Only the write lock** supports `newCondition()`.
  The **read lock does not** (calling `readLock().newCondition()` throws `UnsupportedOperationException`).

---

# Fair vs Unfair behavior (practical notes)

* **Unfair (default)**: maximizes throughput; allows some **barging** (a just-arrived reader may sneak in if lock looks free). Modern impls still try to **avoid permanent writer starvation**, but there’s no strict FIFO guarantee.
* **Fair**: strictly hands the lock to the **longest-waiting** thread. Great for predictability, slightly slower due to queue ordering.

Use **fair** when order matters (e.g., ticketing, financial ledgers). Use **unfair** for raw throughput in typical services.

---

# Real-world use cases

* **In-memory caches**: many reads, occasional cache fill/update.
* **Configuration/feature flags**: read often, write rarely.
* **Catalog/search indexes**: readers scan, background job updates.
* **Game state snapshots**: readers render state; writer applies periodic updates.

---

# Common pitfalls & best practices

* ❌ **Read→Write upgrade** while still holding read lock → **deadlock risk**.
  ✅ Release read, then acquire write; re-check the condition.
* ✅ **Always `unlock()` in `finally`** to avoid leaks & deadlocks.
* ❌ Don’t assume it’s always faster than a simple `ReentrantLock`. If writes are frequent or sections are tiny, the overhead can **hurt** performance.
* ❌ Don’t use `Condition` with the read lock (unsupported).
* ✅ Consider **`StampedLock`** for advanced, read-dominated cases (supports optimistic reads), but it’s **not reentrant** and requires more care. Start with `ReentrantReadWriteLock` unless you’ve profiled.

---

# Side-by-side: `synchronized` vs `ReentrantLock` vs `ReentrantReadWriteLock`

| Tool                     | Concurrency Model                                                              | Best When                                 |
| ------------------------ | ------------------------------------------------------------------------------ | ----------------------------------------- |
| `synchronized`           | Single, implicit monitor (exclusive)                                           | Simple critical sections                  |
| `ReentrantLock`          | Single explicit lock (exclusive) + features (tryLock, interruptible, fairness) | Need advanced control but still exclusive |
| `ReentrantReadWriteLock` | Two locks (many readers OR one writer)                                         | Read-heavy workloads; safe parallel reads |

---

# Minimal runnable demo: racing readers with occasional writer

```java
import java.util.concurrent.locks.*;
import java.util.concurrent.*;
import java.util.*;

public class ReadWriteDemo {
    private static final ReentrantReadWriteLock rw = new ReentrantReadWriteLock(false); // try true for fairness
    private static final Lock r = rw.readLock();
    private static final Lock w = rw.writeLock();
    private static final Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // Preload
        w.lock();
        try { map.put(1, 42); } finally { w.unlock(); }

        ExecutorService pool = Executors.newFixedThreadPool(6);

        // 4 readers
        for (int i = 0; i < 4; i++) {
            int id = i;
            pool.submit(() -> {
                for (int k = 0; k < 5; k++) {
                    r.lock();
                    try {
                        Integer v = map.get(1);
                        System.out.println("Reader " + id + " saw: " + v);
                        Thread.sleep(100);
                    } finally {
                        r.unlock();
                    }
                }
            });
        }

        // 1 writer
        pool.submit(() -> {
            for (int k = 0; k < 3; k++) {
                w.lock();
                try {
                    int newVal = (int)(Math.random() * 1000);
                    System.out.println("Writer updating to: " + newVal);
                    map.put(1, newVal);
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                } finally {
                    w.unlock();
                }
                Thread.sleep(150);
            }
        });

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }
}
```

You’ll observe **many readers running together**, but whenever the writer enters, readers **pause** until the write is done.

---

## TL;DR

* **ReadWriteLock** lets **many readers** run together; **writers** are exclusive.
* Use **`ReentrantReadWriteLock`** for **read-heavy** sections to improve throughput.
* **No read→write upgrade**; you can **downgrade** (write→read).
* Fairness is a constructor flag; default (unfair) is usually faster.
* Always `unlock()` in `finally`, and profile to ensure it actually helps your workload.

