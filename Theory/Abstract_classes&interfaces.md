
---

# 🔹 **Abstract Classes in Java**

An **abstract class** is a class declared using the `abstract` keyword.

* It **cannot be instantiated** (you can’t create objects of it).
* It may contain **abstract methods** (methods without body) and **concrete methods** (normal methods with body).
* It is used when you want to provide a **base class with some common code** and force child classes to implement the rest.

---

### ✅ Abstract Class Example

```java
abstract class Animal {
    // Abstract method (must be implemented by child)
    abstract void sound();

    // Concrete method (already implemented)
    void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();   // Bark
        a.sleep();   // Sleeping...

        Animal b = new Cat();
        b.sound();   // Meow
    }
}
```

### 🔑 Key Points

* An abstract class can have **constructors** (unlike interfaces).
* It can have **instance variables**, **static variables**, and **final variables**.
* Supports **inheritance** (`extends`).
* A subclass must **implement all abstract methods**, unless it’s also declared `abstract`.

---

# 🔹 **Interfaces in Java**

An **interface** is a blueprint of a class that contains **abstract methods** (by default) and constants.

* Declared using `interface` keyword.
* A class implements an interface using `implements`.
* Provides **full abstraction** (before Java 8).
* Supports **multiple inheritance** (a class can implement multiple interfaces).

---

### ✅ Interface Example

```java
interface Animal {
    void sound();   // abstract method
    void sleep();   // since Java 8, can have default method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }

    public void sleep() {
        System.out.println("Sleeping...");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal d = new Dog();
        d.sound();   // Bark
        d.sleep();   // Sleeping...
    }
}
```

---

### 🔑 Interface Features

1. **All variables are public, static, and final** by default.

   ```java
   interface Test {
       int x = 10; // same as public static final int x = 10;
   }
   ```
2. **All methods are public and abstract** by default (except `default`, `static`, `private` methods added in Java 8+).
3. No constructors allowed.
4. A class can **implement multiple interfaces** → solves **multiple inheritance problem**.

---

# 🔹 Default & Static Methods in Interfaces (Java 8+)

Before Java 8 → only abstract methods.
After Java 8 → interfaces can have:

* **default methods** (with body, can be overridden in child)
* **static methods** (called using interface name)

```java
interface Vehicle {
    void drive(); // abstract

    default void horn() {
        System.out.println("Beep Beep!"); // default method
    }

    static void service() {
        System.out.println("Service time!"); // static method
    }
}

class Car implements Vehicle {
    public void drive() {
        System.out.println("Driving car...");
    }
}

public class Test {
    public static void main(String[] args) {
        Car c = new Car();
        c.drive();   // Driving car...
        c.horn();    // Beep Beep!
        Vehicle.service(); // Service time!
    }
}
```

---

# 🔹 Abstract Class vs Interface (Comparison)

| Feature              | Abstract Class                                 | Interface                                                        |
| -------------------- | ---------------------------------------------- | ---------------------------------------------------------------- |
| **Keyword**          | `abstract class`                               | `interface`                                                      |
| **Instantiation**    | Cannot create objects                          | Cannot create objects                                            |
| **Methods**          | Can have abstract + concrete methods           | Only abstract methods (Java 7), default/static methods (Java 8+) |
| **Variables**        | Instance variables, static, final              | Only `public static final`                                       |
| **Constructors**     | Can have                                       | Cannot have                                                      |
| **Inheritance**      | A class can extend only **one** abstract class | A class can implement **multiple interfaces**                    |
| **Access Modifiers** | Abstract methods can have any modifier         | Methods are always `public`                                      |
| **When to use**      | When you want to share code + enforce rules    | When you only want to enforce rules (contract)                   |

---

# 🎯 When to Use What?

* **Abstract class** → Use when you want to provide a **base class** with some **default implementation**.
* **Interface** → Use when you want to define a **contract** (what a class must do, not how).

---

