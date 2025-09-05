
---

# 🔹 1. `start()`

* **What it does:** Creates a **new thread** and executes the `run()` method in it.
* **Important:** Never call `run()` directly if you want a **new thread**.

**Example:**

```java
Thread t1 = new Thread(() -> System.out.println("Running in new thread"));
t1.start();  // creates a new thread
```

---

# 🔹 2. `run()`

* **What it does:** Contains the **code to be executed** by the thread.
* **Important:** If called directly, it runs **like a normal method on the current thread** (no multithreading).

**Example:**

```java
Thread t1 = new Thread(() -> System.out.println("Running"));
t1.run();  // runs on main thread
```

---

# 🔹 3. `sleep(long millis)`

* **What it does:** Pauses the current thread for a given **time in milliseconds**.
* **Important:** Other threads can run while this one sleeps.

**Example:**

```java
System.out.println("Start");
Thread.sleep(2000); // pause 2 seconds
System.out.println("End");
```

* Output: "End" prints **2 seconds later**.

---

# 🔹 4. `join()`

* **What it does:** Waits for a thread to **finish execution** before continuing.
* **Useful:** When you want to ensure one thread **completes** before the next step.

**Example:**

```java
Thread t1 = new Thread(() -> System.out.println("Thread 1 done"));
Thread t2 = new Thread(() -> System.out.println("Thread 2 done"));

t1.start();
t1.join();  // main thread waits for t1
t2.start();
```

* Here, `t2` starts **only after t1 finishes**.

---

# 🔹 5. `setPriority(int priority)` / `getPriority()`

* **What it does:** Suggests the **importance of a thread** to the JVM/OS.
* **Range:** `Thread.MIN_PRIORITY (1)` → `Thread.MAX_PRIORITY (10)`
* **Important:** Priority is a **hint**; OS may ignore it.

**Example:**

```java
Thread t1 = new Thread(() -> System.out.println("High priority"));
t1.setPriority(Thread.MAX_PRIORITY);
System.out.println(t1.getPriority()); // 10
```

---

# 🔹 6. `yield()`

* **What it does:** Suggests the current thread **pause temporarily** to let other threads run.
* **Important:** It’s just a hint; JVM may ignore it.

**Example:**

```java
Thread.yield(); // current thread hints to pause
```

---

# 🔹 7. `interrupt()` / `isInterrupted()`

* **`interrupt()`** → politely asks a thread to **stop sleeping/waiting**.
* **`isInterrupted()`** → checks if the thread has been interrupted.

**Example:**

```java
Thread t1 = new Thread(() -> {
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        System.out.println("Thread was interrupted!");
    }
});
t1.start();
t1.interrupt(); // interrupts t1 immediately
```

---

# 🔹 8. `currentThread()`

* **What it does:** Returns a reference to the **currently executing thread**.

**Example:**

```java
System.out.println(Thread.currentThread().getName()); // main
```

---

# 🔹 9. `getName()` / `setName(String name)`

* **Get or set thread name** for identification.

**Example:**

```java
Thread t1 = new Thread();
t1.setName("MyThread");
System.out.println(t1.getName()); // MyThread
```

---

# 🔹 10. `isAlive()`

* **What it does:** Checks if a thread is **still running**.

**Example:**

```java
Thread t1 = new Thread(() -> {
    System.out.println("Running");
});
t1.start();
System.out.println(t1.isAlive()); // true (might be false if thread finished very fast)
```

---

## 🔹 Summary Table

| Method                | Purpose                      | Important Notes                        |
| --------------------- | ---------------------------- | -------------------------------------- |
| `start()`             | Starts new thread            | Calls `run()` internally               |
| `run()`               | Thread logic                 | Don’t call directly for multithreading |
| `sleep(millis)`       | Pause thread                 | Others can run during sleep            |
| `join()`              | Wait for thread to finish    | Blocks current thread                  |
| `setPriority()`       | Suggest thread importance    | OS may ignore                          |
| `yield()`             | Suggest current thread pause | Just a hint                            |
| `interrupt()`         | Interrupt thread             | Works if thread is sleeping/waiting    |
| `isInterrupted()`     | Check if interrupted         | Boolean flag                           |
| `currentThread()`     | Get running thread           | Returns Thread object                  |
| `getName()/setName()` | Name thread                  | Useful for identification              |
| `isAlive()`           | Check if running             | True until thread dies                 |

---
