
# What is Method Overriding?

**Overriding** happens when a **subclass** provides its **own implementation** for a method that is already defined in its **superclass**.
Purpose: allow a subclass to change or extend the behavior of an inherited method.

Key facts:

* Same **method name**, **parameter list** (signature) and **name** as parent.
* Return type: same or **covariant** (a subtype).
* Access level: cannot be more restrictive than the parent’s.
* Overridden method is chosen at **runtime** (dynamic dispatch).

Use `@Override` annotation — it’s good practice (compiler checks you’re actually overriding).

```java
class Parent {
    public void greet() { System.out.println("Hello from Parent"); }
}

class Child extends Parent {
    @Override
    public void greet() { System.out.println("Hello from Child"); }
}

public class Demo {
    public static void main(String[] args) {
        Parent p = new Child();
        p.greet(); // prints: Hello from Child
    }
}
```

# What is Polymorphism?

**Polymorphism** = “many forms”. In Java it usually refers to **reference polymorphism**: a reference of a parent type can point to objects of different subclasses. The actual method executed depends on the **object’s runtime type**, not the reference type.

Two flavors:

* **Compile-time polymorphism** (method overloading) — decided at compile time.
* **Runtime polymorphism** (overriding) — decided at runtime (dynamic dispatch).

Example of runtime polymorphism (above): `Parent p = new Child();` — method executed is `Child.greet()`.

# How Java resolves overridden methods

1. Compiler checks method existence using the **reference type** (so only methods visible in that type can be called).
2. At runtime, JVM finds the **actual object's** overridden implementation and calls that.

```java
Parent p = new Child();
p.greet();        // compile OK (Parent has greet), runtime calls Child.greet
// p.special();   // compile error if Parent has no special()
((Child)p).special(); // cast to call subclass-only method
```

# Rules & Restrictions for Overriding

* Method signature must match exactly (name + parameter types/order).
* Return type: same or **covariant** (subtype).

  ```java
  class A {}
  class B extends A {}
  class Parent { A get() { ... } }
  class Child extends Parent { @Override B get() { ... } } // OK
  ```
* Access modifier: cannot reduce visibility (e.g., `public` → `protected` not allowed).
* `static` methods **are not overridden** — they are **hidden**. They’re resolved by reference type at compile time.
* `final` methods **cannot be overridden**.
* `private` methods are **not visible** to subclass, so they cannot be overridden (they can be re-declared — that's method hiding).
* Exceptions: a child overriding method cannot throw **broader checked exceptions** than the parent method. It can throw fewer or narrower checked exceptions or any unchecked exceptions.

# Overriding vs Overloading (quick recap)

* **Overriding**: same signature, different class (runtime polymorphism).
* **Overloading**: same name, different parameter list in the same class (compile-time polymorphism).

# Examples — runnable and explained

### 1) Basic overriding + polymorphism

```java
class Animal {
    String name = "Animal";
    public void sound() { System.out.println("Some generic sound"); }
}

class Dog extends Animal {
    @Override
    public void sound() { System.out.println("Bark"); }
    public void wag() { System.out.println("Dog wags tail"); }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();     // Bark  (runtime dispatch)
        // a.wag();    // Compile error: wag not in Animal
        ((Dog)a).wag(); // cast to Dog -> Dog wags tail
    }
}
```

### 2) Static method hiding (not overriding)

```java
class Parent {
    static void stat() { System.out.println("Parent static"); }
    void inst() { System.out.println("Parent instance"); }
}

class Child extends Parent {
    static void stat() { System.out.println("Child static"); } // hides
    @Override
    void inst() { System.out.println("Child instance"); }     // overrides
}

public class Demo {
    public static void main(String[] args) {
        Parent p = new Child();
        p.stat();   // Parent static  <-- static called on reference type
        p.inst();   // Child instance  <-- instance uses runtime type
    }
}
```

### 3) Covariant return types & exceptions

```java
class Animal {}
class Dog extends Animal {}

class Parent {
    public Animal get() throws Exception { return new Animal(); }
}

class Child extends Parent {
    @Override
    public Dog get() throws RuntimeException { return new Dog(); } // OK: covariant + narrower exception (unchecked)
}
```

# Common pitfalls & gotchas

* Expecting `static` methods to follow polymorphism — they don’t.
* Making overridden method less accessible (will fail compile).
* Changing return type to an incompatible type (compile error unless covariant).
* Overriding and throwing a broader checked exception (compile error).
* Forgetting `@Override` and accidentally overloading instead.
* Casting wrong type at runtime → `ClassCastException`.

# Why this matters (design & SOLID)

* Overriding + polymorphism enables **runtime behavior substitution** — you can code to interfaces/supertypes and plug in implementations later (Liskov Substitution Principle).
* Makes code extensible and decoupled (e.g., `List l = new ArrayList()`).

# Quick real-world analogy

Think of `Vehicle` as an interface: `start()` is the common action. `Car` and `Bike` override `start()` differently. If you ask “start” on any `Vehicle`, the actual vehicle decides how to start (key, button, kick). You don’t need to know which one it is.

# Short checklist (for interviews)

* Overriding = same signature in subclass.
* Use `@Override`.
* Overridden method selected at **runtime**.
* `static` methods are hidden; `final` and `private` cannot be overridden.
* Covariant return types allowed.
* Overridden method cannot broaden checked exceptions or reduce visibility.

---

