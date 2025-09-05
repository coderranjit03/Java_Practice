
---

# 📝 Thread Communication in Java (Detailed Guide)

---

## 🔹 1. What is Thread Communication?

When multiple threads work together, they often need to **coordinate**.
For example:

* One thread **produces data**.
* Another thread **consumes data**.

👉 Without communication, either:

* Consumer may try to read before producer writes.
* Producer may overwrite before consumer reads.

To avoid this, Java provides **Inter-Thread Communication** using **wait–notify mechanism**.

---

## 🔹 2. Why Do We Need Thread Communication?

Consider the **Producer–Consumer Problem**:

* **Producer** keeps adding items to a buffer.
* **Consumer** removes items from the buffer.

Problems without communication:

* Producer may add items when buffer is already full.
* Consumer may try to consume when buffer is empty.

✅ Solution → Communication between threads using `wait()`, `notify()`, and `notifyAll()`.

---

## 🔹 3. Methods for Thread Communication

All three methods are defined in the **Object class** (not in Thread class).
That’s because **every object in Java can be used as a monitor (lock)**.

### 1. `wait()`

* Causes the current thread to **release the lock** and **wait** until another thread calls `notify()` or `notifyAll()` on the same object.
* Must be called from inside a `synchronized` block.

### 2. `notify()`

* Wakes up **one waiting thread** (chosen arbitrarily by JVM) that is waiting on the same object’s monitor.

### 3. `notifyAll()`

* Wakes up **all waiting threads** on that object.

---

## 🔹 4. Working Principle (Step by Step)

1. Thread **acquires the lock** (via `synchronized`).
2. Thread calls `wait()` → releases the lock → moves to **waiting state**.
3. Another thread acquires the lock, does work, then calls `notify()` / `notifyAll()`.
4. The waiting thread(s) wake up, **reacquire the lock**, and continue execution.

👉 Only one thread can hold the lock at a time.

---

## 🔹 5. Example: Producer–Consumer Problem

### Code:

```java
class SharedResource {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) {
        while (hasData) { // wait if data not yet consumed
            try { wait(); } catch (InterruptedException e) {}
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify(); // notify consumer
    }

    public synchronized void consume() {
        while (!hasData) { // wait if no data produced
            try { wait(); } catch (InterruptedException e) {}
        }
        System.out.println("Consumed: " + data);
        hasData = false;
        notify(); // notify producer
    }
}

public class ThreadCommunicationExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();
    }
}
```

### Output:

```
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
...
```

---

## 🔹 6. Important Points to Remember

* `wait()`, `notify()`, and `notifyAll()` **must be called inside synchronized blocks**.
* `wait()` releases the lock, but `sleep()` does not.
* If multiple threads are waiting, `notify()` wakes up only one, while `notifyAll()` wakes up all.
* Using `while` instead of `if` before `wait()` is recommended → prevents **spurious wakeups**.

---

## 🔹 7. Real-World Analogy

Imagine:

* Producer = Chef cooking food.
* Consumer = Waiter serving food.
* Buffer = Table.

Rules:

* Chef waits if table already has food.
* Waiter waits if table is empty.
* When Chef puts food → notifies Waiter.
* When Waiter picks food → notifies Chef.

---

## 🔹 8. Advanced Alternatives

* `wait/notify` is **low-level**.
  Modern Java provides better constructs:
* **BlockingQueue** (`ArrayBlockingQueue`, `LinkedBlockingQueue`)
* **Semaphore**
* **CountDownLatch**
* **CyclicBarrier**

👉 These are easier and safer for thread communication.

---

# ✅ Summary

* **Thread communication** solves coordination problems (like producer-consumer).
* Uses `wait()`, `notify()`, and `notifyAll()`, always inside `synchronized`.
* `wait()` releases lock, `sleep()` does not.
* Use `while` for waiting conditions.
* Modern concurrency utilities (BlockingQueue, Semaphore, etc.) are preferred for real-world apps.

---

