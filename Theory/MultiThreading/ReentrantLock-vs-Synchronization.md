
---

# 🔹 `synchronized` vs `ReentrantLock`

### 1. **Automatic vs Manual Locking**

* **`synchronized`** → lock is **acquired automatically** when entering the block/method and **released automatically** when exiting.
* **`ReentrantLock`** → you **manually acquire** (`lock()`) and **release** (`unlock()`).

  * If you forget to call `unlock()`, you may cause a deadlock.
  * But manual control gives you **flexibility**.

---

### 2. **Try Locking**

* **`synchronized`** → if a thread can’t get the lock, it **waits forever** until lock is free.
* **`ReentrantLock`** → has **`tryLock()`**, so a thread can:

  * Try to acquire lock.
  * If not available, do something else instead of waiting.

👉 Example:

```java
if(lock.tryLock()) {
    try {
        // critical section
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Lock not available, doing something else...");
}
```

---

### 3. **Fairness Policy**

* **`synchronized`** → no fairness, JVM decides which waiting thread gets the lock.
* **`ReentrantLock`** → you can create a **fair lock** (`new ReentrantLock(true)`) that gives the lock to the **longest waiting thread (FIFO)**.

👉 Useful in systems like **banking** where order matters.

---

### 4. **Interruptible Lock**

* **`synchronized`** → once a thread waits for a lock, it **cannot be interrupted**.
* **`ReentrantLock`** → supports **`lockInterruptibly()`**, so waiting threads can be **interrupted** (cancelled).

👉 Example: in a server, if a thread is waiting too long, you can **stop it safely**.

---

### 5. **Condition Variables (Advanced Feature)**

* **`synchronized`** → works with `wait()`, `notify()`, `notifyAll()` for thread communication.
* **`ReentrantLock`** → provides **Condition objects** (`newCondition()`), which are **more powerful & flexible** (multiple wait-sets).

---

### 6. **Reentrancy**

* Both are **reentrant** → means if the same thread already holds the lock, it can acquire it again without blocking itself.

---

# 🔹 Example Comparison

### Using `synchronized`

```java
public synchronized void increment() {
    count++;
}
```

### Using `ReentrantLock`

```java
ReentrantLock lock = new ReentrantLock();

public void increment() {
    lock.lock();    // acquire
    try {
        count++;
    } finally {
        lock.unlock(); // release
    }
}
```

---

# 🔹 When to Use Which?

✅ Use **`synchronized`** if:

* Your critical section is simple.
* You don’t need advanced features.
* You want cleaner and less error-prone code.

✅ Use **`ReentrantLock`** if:

* You need **fairness, tryLock, interruptible locks, or multiple condition variables**.
* You’re building complex systems (servers, banking apps, real-time apps).
* You want more **fine-grained control** over locking.

---

👉 **In simple words:**

* `synchronized` = Easy to use, automatic, but limited.
* `ReentrantLock` = More **power & flexibility**, but you must manage locks carefully.


---

# 🔹 `synchronized` vs `ReentrantLock`

| Feature                   | `synchronized` (Keyword) 🚦         | `ReentrantLock` (Class) 🛡️                           |
| ------------------------- | ----------------------------------- | ----------------------------------------------------- |
| **Locking/Unlocking**     | Automatic (entering/exiting block)  | Manual (`lock()` + `unlock()`)                        |
| **If Lock Not Available** | Thread waits forever                | `tryLock()` allows skipping or timeout                |
| **Fairness (FIFO order)** | Not guaranteed                      | Can be set (`new ReentrantLock(true)`)                |
| **Interruptible Waiting** | Not possible                        | Possible with `lockInterruptibly()`                   |
| **Condition Variables**   | Only 1 wait-set (`wait()/notify()`) | Multiple `Condition` objects (`await()/signal()`)     |
| **Reentrancy**            | Yes                                 | Yes                                                   |
| **Ease of Use**           | Very simple, less error-prone       | More flexible but more code                           |
| **Best For**              | Simple synchronization              | Complex concurrent apps (servers, banking, real-time) |

---

# 🔹 Real-World Analogy

### `synchronized` = 🚦 **Traffic Signal**

* Cars (threads) approach an intersection (critical section).
* Signal (lock) turns green automatically for one direction at a time.
* Drivers don’t decide anything — they just **wait until it’s green**.
* Simple and automatic ✅ but **no fairness** (sometimes one side gets more green time).

---

### `ReentrantLock` = 🛡️ **Security Guard**

* Guard controls access to a room (critical section).
* Car (thread) arrives → asks guard for permission.
* Guard can:

  * **Give access** (`lock()`)
  * **Deny politely** (`tryLock()`)
  * **Follow FIFO rule** (fair lock)
  * **Kick out a waiting person if emergency** (`lockInterruptibly()`)
* More **flexible & fair**, but you need to **hire & manage the guard** (manually unlock).

---

✅ **In interviews, you can say:**

* *“`synchronized` is like a traffic light — automatic but less flexible. `ReentrantLock` is like a security guard — you control how access is given, but you must also handle the responsibilities.”*

---

