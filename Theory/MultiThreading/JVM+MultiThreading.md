
---

# 🔹 Multithreading in Java & JVM

Java supports multithreading at the **language level**, but it’s the **JVM and OS** that actually run threads on CPU cores.

---

## 1️⃣ Java Thread Basics

* In Java, you can create a thread in **two ways**:

  1. **Extending Thread class**

  ```java
  class MyThread extends Thread {
      public void run() {
          System.out.println("Thread running: " + Thread.currentThread().getName());
      }
  }

  public class Demo {
      public static void main(String[] args) {
          MyThread t1 = new MyThread();
          t1.start();  // starts a new thread
      }
  }
  ```

  2. **Implementing Runnable interface**

  ```java
  class MyRunnable implements Runnable {
      public void run() {
          System.out.println("Thread running: " + Thread.currentThread().getName());
      }
  }

  public class Demo {
      public static void main(String[] args) {
          Thread t1 = new Thread(new MyRunnable());
          t1.start();
      }
  }
  ```

* **`start()`** method: tells JVM to schedule this thread for execution.

* **`run()`** method: contains the code the thread executes.

---

## 2️⃣ JVM Thread Scheduling

* JVM delegates thread scheduling to the **underlying OS**.

* The **OS scheduler** decides:

  * Which thread runs on which **CPU core**.
  * How long it runs (time slice).
  * Thread priority (Java thread priority is just a hint to OS).

* JVM maintains a **Thread object** for each Java thread.

* Internally, each Java thread maps to a **native OS thread**.

---

## 3️⃣ Thread Lifecycle in JVM

Every thread in JVM goes through **5 states**:

| State                       | Description                                                   |
| --------------------------- | ------------------------------------------------------------- |
| **New**                     | Thread object created, not yet started (`new Thread()`)       |
| **Runnable**                | Thread is ready to run (`start()` called)                     |
| **Running**                 | Thread is executing on a CPU core                             |
| **Waiting / Timed Waiting** | Thread is waiting for a signal or sleep (`wait()`, `sleep()`) |
| **Terminated**              | Thread has finished execution                                 |

---

## 4️⃣ Multithreading Execution

* JVM handles **concurrent execution** by mapping **Java threads → OS threads**.
* JVM uses **preemptive scheduling**: OS may **pause a thread** and switch to another.
* Threads may run in **parallel** (on multi-core CPU) or **concurrently** (single-core CPU time-slicing).

---

## 5️⃣ Synchronization & Safety

* When multiple threads access **shared memory**, JVM provides **synchronization mechanisms**:

  * `synchronized` keyword
  * Locks (`ReentrantLock`)
  * `volatile` variables
* JVM ensures **mutual exclusion** to prevent data inconsistency.

---

## 6️⃣ Example of Multithreading in JVM

```java
class Counter extends Thread {
    static int count = 0;

    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized(Counter.class) {  // JVM ensures thread-safe increment
                count++;
            }
        }
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        Counter t1 = new Counter();
        Counter t2 = new Counter();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count = " + Counter.count);
    }
}
```

* JVM handles **thread creation**, **OS-level scheduling**, and **synchronization** to safely increment `count`.

---

Ah, this is an **important Java multithreading concept** — many beginners get confused here. Let’s clarify carefully.

---

## 🔹 Why we don’t call `run()` directly

In your code:

```java
Thread t1 = new Thread(new MyRunnable());
t1.start();
```

* **`run()`**: This is just a **normal method** that defines what the thread will do.

  * If you **call `t1.run()` directly**, it executes **like a normal method on the main thread**.
  * No new thread is created; it **does not run concurrently**.

* **`start()`**: This is the **magic method** that actually tells the JVM to:

  1. **Create a new thread in the OS**.
  2. Put it in the **Runnable state**.
  3. Schedule it for execution.
  4. **Call the `run()` method internally** on that new thread.

---

## 🔹 Analogy

* Think of **`run()`** as **the task list** (what to do).
* **`start()`** is **hiring a new worker (thread) to do that task**.

If you just call `run()`, it’s like you’re doing the task yourself on the **main worker**, not hiring a new worker.

---

## 🔹 Example: Direct `run()` vs `start()`

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

public class Demo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());

        t1.run();   // Runs on main thread
        t1.start(); // Runs on a new thread
    }
}
```

**Possible Output:**

```
Thread running: main
Thread running: Thread-0
```

* First line → `t1.run()` runs **on main thread**.
* Second line → `t1.start()` runs **on a new thread (Thread-0)**.

---

## 🔹 Important Points

* **Do not call `run()` directly** if you want multithreading — it will just run like a normal method on the main thread.
* **`start()` handles thread creation + scheduling + calls `run()` internally**.
* `run()` itself is **never called automatically by your code**; you always invoke it **indirectly via `start()`** to execute in a new thread.

---

## 🔹 Key Point

* **`run()`** = the code to execute
* **`start()`** = create a **new thread** and execute `run()` concurrently

---

## 🔹 Summary

* Java threads are **managed by JVM**, but actual execution depends on **OS scheduler and CPU cores**.
* JVM maps **each Java thread to a native OS thread**.
* JVM provides **thread lifecycle, scheduling, and synchronization** to safely run multithreaded applications.
* Multithreading improves **performance and responsiveness**, especially on multi-core CPUs.

---



