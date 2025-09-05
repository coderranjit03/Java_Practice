

# 🔹 What is Synchronization in Java?

* **Problem:** When **multiple threads** access **shared resources** (like a variable, file, or object) **at the same time**, data can become **inconsistent**.
* **Solution:** **Synchronization** ensures that **only one thread** accesses the shared resource **at a time**.

---

## 🔹 Example Problem (Without Synchronization)

```java
class Counter {
    int count = 0;

    public void increment() {
        count++;  // not atomic
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count = " + c.count);  // May be < 2000
    }
}
```

* Here, `count++` is **not atomic** (it has 3 steps: read, increment, write).
* If **t1 and t2 run simultaneously**, they may overwrite each other → **wrong result**.

---

# 🔹 What is `synchronized`?

* **Keyword** used to **lock a method or block of code** so that **only one thread** can execute it at a time.
* The lock is **per object** (or **per class** for static methods).

---

## 🔹 1. Synchronized Method

```java
class Counter {
    int count = 0;

    public synchronized void increment() {  // only one thread at a time
        count++;
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) c.increment(); });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Count = " + c.count); // Always 2000
    }
}
```

* Now, JVM ensures **t1 and t2 cannot execute `increment()` simultaneously**.
* Result is **consistent**.

---

## 🔹 2. Synchronized Block

* Sometimes, you don’t need to synchronize the **whole method**, only a **part of it**.

```java
class Counter {
    int count = 0;

    public void increment() {
        synchronized(this) {  // only lock this part
            count++;
        }
    }
}
```

* **`this`** → lock on the current object.
* Only one thread can enter this block **at a time**.
* Other threads must **wait** until the lock is released.

---

## 🔹 Key Points about `synchronized`

1. **Locks are object-level**

   * Each object has a separate lock.
   * If two threads work on **different objects**, they can run **simultaneously**.

2. **Static synchronized methods**

   * Lock is on the **class object**, not instance.
   * Ensures only **one thread** can execute static synchronized methods **across all instances**.

3. **Thread Safety**

   * Synchronization guarantees **mutual exclusion**, preventing **race conditions**.

4. **Downside**

   * Overuse can **reduce performance**, because threads wait for the lock.

---

## 🔹 Analogy (Bank Counter)

* Shared resource = **Bank account balance**.
* Multiple threads = **customers** trying to withdraw/deposit at the same time.
* **synchronized** = only **one customer at a time** can use the counter → no mistakes.

---

# 🔹 1. Race Condition

**Definition:**
A **race condition** happens when **two or more threads** try to **read and write shared data simultaneously**, and the **final result depends on the order of execution**.

**Key point:** The program may work correctly sometimes and fail at other times → **unpredictable results**.

---

## 🔹 Example of Race Condition

```java
class Counter {
    int count = 0;

    public void increment() {
        count++; // not atomic
    }
}

public class RaceDemo {
    public static void main(String[] args) throws Exception {
        Counter c = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) c.increment();
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Count = " + c.count); // May be less than 2000
    }
}
```

**Why it happens:**

* `count++` actually does 3 steps:

  1. Read `count`
  2. Increment
  3. Write back
* If **t1 and t2** interleave, one thread may overwrite the increment of another → **lost updates**.

---

# 🔹 2. Deadlock

**Definition:**
A **deadlock** occurs when **two or more threads** are **waiting for each other to release locks** indefinitely.

**Example:**

```java
class Resource {
    synchronized void methodA(Resource r) {
        try { Thread.sleep(100); } catch(Exception e){}
        r.methodB(this);
    }

    synchronized void methodB(Resource r) {}
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Resource r1 = new Resource();
        Resource r2 = new Resource();

        Thread t1 = new Thread(() -> r1.methodA(r2));
        Thread t2 = new Thread(() -> r2.methodA(r1));

        t1.start(); t2.start();
    }
}
```

* **What happens:**

  * t1 locks r1 and waits for r2
  * t2 locks r2 and waits for r1
  * Both **wait forever** → deadlock

---

# 🔹 3. Starvation

**Definition:**
**Starvation** occurs when a thread is **perpetually denied CPU** because **higher-priority threads** always run first.

**Example:**

* Suppose a **low-priority thread** wants to enter a synchronized block.
* If **high-priority threads** continuously acquire the lock, the low-priority thread may **never get a chance** → starvation.

---

# 🔹 4. Livelock

**Definition:**
**Livelock** is similar to deadlock, but threads **keep running and trying to acquire locks**, without making progress.

* Think of **two people trying to pass in a narrow hallway**, both keep stepping aside to let the other go → nobody moves.

---

# 🔹 Why Race Conditions Happen

* Threads **share data** and **access it concurrently**.
* Operations are **not atomic**.
* No proper **synchronization/locking** is used.

---

# 🔹 How to Prevent

| Problem        | Solution                                                                 |
| -------------- | ------------------------------------------------------------------------ |
| Race Condition | Use `synchronized` keyword, locks, or atomic variables (`AtomicInteger`) |
| Deadlock       | Lock ordering, try-lock, avoid nested locks                              |
| Starvation     | Fair locks, thread priority adjustment                                   |
| Livelock       | Limit retries, back-off strategies                                       |

---

# 🔹 Analogy (Race Condition)

* **Shared bank account** with \$100.
* **Two ATMs (threads)** withdraw \$50 simultaneously.
* Without synchronization → both read \$100, subtract \$50, write back → account shows \$50 instead of \$0 → **lost update**.

---

✅ **Key Takeaways**

1. **Race condition** = threads fight over shared data → inconsistent results
2. **Deadlock** = threads wait forever for each other
3. **Starvation** = some threads never get CPU
4. **Livelock** = threads keep active but make no progress

---

✅ **Summary**

* **Synchronization** = controlling access to shared resources by multiple threads.
* **`synchronized` keyword** = locks **methods** or **blocks** to ensure **only one thread executes at a time**.
* Essential for **thread safety** when multiple threads **share data**.

---
