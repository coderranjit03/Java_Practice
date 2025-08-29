
---

# 🔹 What is Multiple Inheritance?

👉 **Multiple Inheritance** means a class can inherit from more than one parent.

* **Problem in Java:** If a class extends multiple classes, ambiguity can occur (like the **Diamond Problem**).
* **Solution in Java:** Java does **NOT allow multiple inheritance with classes**, but it **allows multiple inheritance with interfaces** ✅.

---

# 🔹 Why Interfaces Support Multiple Inheritance?

* Interfaces only define **method signatures (contracts)**.
* No actual method body (before Java 8), so **no conflict in implementation**.
* Hence, a class can safely implement multiple interfaces without ambiguity.

---

# ✅ Example: Multiple Inheritance using Interfaces

```java
// First interface
interface A {
    void methodA();
}

// Second interface
interface B {
    void methodB();
}

// Class implementing multiple interfaces
class C implements A, B {
    public void methodA() {
        System.out.println("Method A from Interface A");
    }

    public void methodB() {
        System.out.println("Method B from Interface B");
    }
}

public class Test {
    public static void main(String[] args) {
        C obj = new C();
        obj.methodA(); // Method A from Interface A
        obj.methodB(); // Method B from Interface B
    }
}
```

👉 Here, `C` is inheriting behavior from **both A and B**.

---

# 🔹 Diamond Problem with Interfaces

If two interfaces have the **same default method**, and a class implements both → **ambiguity arises**.

---

### ⚡ Example of Diamond Problem

```java
interface A {
    default void show() {
        System.out.println("A's show");
    }
}

interface B {
    default void show() {
        System.out.println("B's show");
    }
}

// Class implementing both
class C implements A, B {
    // Must override to resolve conflict
    public void show() {
        System.out.println("C's show (resolving ambiguity)");
    }
}

public class Test {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();  // C's show (resolving ambiguity)
    }
}
```

👉 Java forces you to **override** the method to resolve conflict.

---

# 🔹 Multiple Inheritance Summary

✅ **Allowed:**

* A class can implement **multiple interfaces**.
* An interface can also **extend multiple interfaces**.

❌ **Not Allowed:**

* A class cannot extend multiple classes.

---

# ✅ Example: Interface Extending Multiple Interfaces

```java
interface A {
    void methodA();
}

interface B {
    void methodB();
}

// Interface extending multiple
interface C extends A, B {
    void methodC();
}

class D implements C {
    public void methodA() {
        System.out.println("Method A");
    }
    public void methodB() {
        System.out.println("Method B");
    }
    public void methodC() {
        System.out.println("Method C");
    }
}

public class Test {
    public static void main(String[] args) {
        D obj = new D();
        obj.methodA();
        obj.methodB();
        obj.methodC();
    }
}
```

---

# 🎯 Key Takeaways

* Java **does not support multiple inheritance with classes** (to avoid ambiguity).
* But it **supports multiple inheritance with interfaces**.
* If two interfaces provide the same default method → the implementing class **must override** it to resolve ambiguity.

---

