
---

## 🔹 What is a Critical Section?

* A **critical section** is a **piece of code** that **accesses a shared resource** (like a variable, file, or database) that **should not be accessed by more than one thread at the same time**.
* If two or more threads enter it **simultaneously**, we get problems like **race conditions**.

👉 Think of it like a **toilet in a house** 🚻 — only one person can use it at a time, otherwise chaos happens.

---

## 🔹 Why Threads Want to Enter the Critical Section?

Because **threads often share resources**:

* Example: Two threads increasing a counter `count++`.
* Both want to **read, update, and write** to the same variable.
* If they do it at the same time, one update might get lost.

👉 Each thread wants to **enter the critical section** to safely **do its work** on the shared data.

---

## 🔹 How It Works?

1. **Identify shared resource** (e.g., variable, file, list).
2. **Mark code as critical section** (using `synchronized` or `Lock`).
3. When a thread reaches this section:

   * It must **acquire a lock**.
   * If lock is free → it enters.
   * If lock is busy → it waits.
4. Once thread finishes work, it **releases the lock** so the next one can enter.

👉 This ensures only **one thread at a time** touches the shared data.

---

## 🔹 Example in Java (without synchronization — ❌ Problem)

```java
class Counter {
    int count = 0;

    public void increment() {
        count++; // critical section
    }
}

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Count = " + c.count); // Expected: 2000, Actual: <2000 ❌
    }
}
```

👉 Because both threads enter the **critical section** at the same time, updates get lost.

---

## 🔹 Example with Synchronization (✅ Fixed)

```java
class Counter {
    int count = 0;

    public synchronized void increment() { // critical section protected
        count++;
    }
}
```

👉 Now, **only one thread** can run `increment()` at a time → no race condition.

---

## 🔹 Real-World Examples of Critical Section

* **Google Chrome**:

  * Multiple tabs running threads, but **all writing to the browsing history file** → only one at a time allowed.
* **MS Word**:

  * Auto-save thread writing to a document file, while another thread is formatting → they need synchronization.
* **Banking System**:

  * Two threads updating the same bank account balance → must lock to avoid inconsistent money.

---

✅ **In Simple Words**:

* **Critical Section** = A sensitive zone where shared resources are modified.
* **Why threads enter?** → They need to use/update shared data.
* **How it works?** → By using locks/synchronized so only one thread at a time can enter.


---

# 🔹 What are Locks in Java?

* A **lock** is like a **key** that a thread must acquire before it can enter a **critical section** (a part of code that modifies shared data).
* Only **one thread** can hold the lock at a time → prevents **race conditions**.
* Once the thread finishes, it **releases the lock**, allowing others to proceed.

👉 In simple terms: **Locks control access to shared resources among threads.**

---

# 🔹 Why use Locks instead of `synchronized`?

* `synchronized` is simple but **limited**:

  * Can’t **try** to acquire a lock and give up if unavailable.
  * Doesn’t allow **interrupting a waiting thread**.
  * No **fairness guarantee** (some threads may starve).

👉 `ReentrantLock` gives **more control** and flexibility than `synchronized`.

---

# 🔹 What is `ReentrantLock`?

* Part of `java.util.concurrent.locks` package.
* **Reentrant** = A thread that already holds the lock can acquire it again **without deadlock**.

  * Example: If method A locks, and it calls method B which also locks → no problem, same thread can re-enter.

**Basic Example:**

```java
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();  // acquire lock
        try {
            count++;
        } finally {
            lock.unlock();  // always release in finally
        }
    }

    public int getCount() {
        return count;
    }
}

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Final Count = " + c.getCount()); // always 2000
    }
}
```

---

# 🔹 Important Methods of `ReentrantLock`

| Method                              | Explanation                                                                                      |
| ----------------------------------- | ------------------------------------------------------------------------------------------------ |
| `lock()`                            | Acquires the lock. If unavailable, waits until it’s free.                                        |
| `unlock()`                          | Releases the lock. Must always be in `finally` block.                                            |
| `tryLock()`                         | Tries to acquire the lock immediately. If not available, returns `false` instead of waiting.     |
| `tryLock(long time, TimeUnit unit)` | Tries to acquire the lock within the given time. Returns `true` if acquired, `false` otherwise.  |
| `lockInterruptibly()`               | Acquires the lock unless the thread is interrupted while waiting. Useful to avoid deadlock.      |
| `isLocked()`                        | Returns `true` if any thread currently holds the lock.                                           |
| `isHeldByCurrentThread()`           | Checks if the current thread is holding the lock.                                                |
| `getHoldCount()`                    | Number of times the current thread has acquired the lock (because it’s reentrant).               |
| `getQueueLength()`                  | Number of threads waiting for this lock.                                                         |
| `newCondition()`                    | Creates a `Condition` object for advanced thread communication (similar to `wait()`/`notify()`). |

---

# 🔹 Example with `tryLock()`

```java
if (lock.tryLock()) {
    try {
        System.out.println("Lock acquired, working...");
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Could not acquire lock, doing something else");
}
```

👉 Here, the thread won’t **wait forever** — it just tries once.

---

# 🔹 Example with `lockInterruptibly()`

```java
try {
    lock.lockInterruptibly(); // can be interrupted
    try {
        System.out.println("Work with lock");
    } finally {
        lock.unlock();
    }
} catch (InterruptedException e) {
    System.out.println("Thread was interrupted while waiting for lock");
}
```

👉 Useful in scenarios where you don’t want threads stuck forever (avoiding deadlock).

---

# 🔹 Key Points

1. Always **release the lock** in a `finally` block → avoids deadlock.
2. `ReentrantLock` = more powerful than `synchronized`.
3. Supports **fairness** (FIFO lock ordering) if enabled:

   ```java
   ReentrantLock lock = new ReentrantLock(true); // fair lock
   ```
4. Use `tryLock()` and `lockInterruptibly()` for **flexible control**.

---

✅ **Summary in Simple Words**

* `synchronized` = automatic lock, simple but rigid.
* `ReentrantLock` = manual lock, more powerful (try, timeout, fairness, reentrancy).
* Always remember: **lock → try → unlock** pattern.

---

# 🔹 Faireness in `ReentrantLock`



# 🔹 What is Fairness in Locks?

* **Fairness** = the order in which threads get the lock.
* When multiple threads are waiting for a lock, **who should get it next?**

---

## 1. **Unfair Lock (Default in Java)**

* By default, `synchronized` and `ReentrantLock(false)` are **unfair**.
* The **scheduler/JVM** decides which waiting thread gets the lock → no guarantee of order.
* Sometimes, a newly arrived thread might **“jump the queue”** and get the lock before threads that were already waiting.
* This can cause **starvation** (a thread waits too long and never gets a chance).

👉 Example:
Imagine a busy coffee shop ☕:

* There’s a line of people (threads).
* But the barista sometimes serves **the loudest customer** who just arrived (new thread).
* People in line may keep waiting longer than expected.

---

## 2. **Fair Lock**

* With `ReentrantLock(true)` you can enable **fairness**.
* A fair lock gives the lock to the **longest waiting thread first (FIFO)**.
* Ensures **no starvation** — everyone eventually gets their turn.

👉 Example:
Same coffee shop ☕:

* This time, the barista always serves **the first person in the queue**.
* Even if a new customer shouts, they still wait.
* **Fair, but slower** (because enforcing strict order has extra overhead).

---

# 🔹 Java Example

```java
import java.util.concurrent.locks.ReentrantLock;

class FairnessDemo {
    private ReentrantLock lock = new ReentrantLock(true); // true = fair lock

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " got the lock");
            Thread.sleep(1000); // simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessDemo demo = new FairnessDemo();
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(demo::accessResource, "Thread-" + i);
            t.start();
        }
    }
}
```

👉 Output with **fair lock**:

```
Thread-1 got the lock
Thread-2 got the lock
Thread-3 got the lock
Thread-4 got the lock
Thread-5 got the lock
```

👉 Output with **unfair lock** (`new ReentrantLock(false)`):

```
Thread-1 got the lock
Thread-3 got the lock
Thread-2 got the lock
Thread-5 got the lock
Thread-4 got the lock
```

(Threads may jump the order.)

---

# 🔹 Summary

* **Unfair Lock (default):** Faster, but no guarantee of order → possible starvation.
* **Fair Lock:** Slower, but ensures FIFO order → no starvation.

---

✅ **In simple words:**
Fairness in locks = *“Should threads get the lock in the order they arrived, or should we let the scheduler decide?”*

---
