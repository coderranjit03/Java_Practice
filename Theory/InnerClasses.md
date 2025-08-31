
---

# 🔹 1. What is an Inner Class?

An **inner class** is a class **defined inside another class**.
👉 Think of it like a **helper class** that belongs only to its outer class.

Why use inner classes?

* To logically group classes that are **only used inside another class**.
* To make code **more readable and encapsulated**.
* Inner class can **access private members** of the outer class directly.



**Advantages:**

1. **Logical grouping** → Keep related classes together.
2. **Encapsulation** → Inner class can access `private` members of the outer class.
3. **Cleaner code** → Avoids creating unnecessary separate files.
4. **Callbacks & Listeners** → Anonymous inner classes are widely used in GUI frameworks and Android apps.


---

# 🔹 2. Types of Inner Classes in Java

Java provides **4 types of inner classes**:

1. **Member Inner Class** (Normal inner class inside another class)
2. **Static Inner Class** (Like a static member of outer class)
3. **Local Inner Class** (Defined inside a method)
4. **Anonymous Inner Class** (Class without a name, used for one-time use)

---

## ✅ (A) Member Inner Class

* A **normal inner class** defined inside another class (not static).
* Has access to **all members** (including `private`) of the outer class.
* Needs an **outer object** to create an inner object.

### Example:

```java
class Outer {
    private String msg = "Hello from Outer";

    class Inner { // Member Inner Class
        void display() {
            System.out.println("Message: " + msg); // can access private outer fields
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();         // create outer object
        Outer.Inner inner = outer.new Inner(); // create inner object
        inner.display();
    }
}
```

---

### ❓ Why use Member Inner Class instead of creating a separate class?

**Case 1: Without inner class**

```java
class Engine {
    public void start() {
        System.out.println("Engine starting...");
    }
}

class Car {
    Engine engine = new Engine(); // separate class
    public void drive() {
        engine.start();
        System.out.println("Car is moving...");
    }
}
```

**Case 2: With inner class**

```java
class Car {
    class Engine {  // inner class
        public void start() {
            System.out.println("Engine starting...");
        }
    }

    public void drive() {
        Engine engine = new Engine(); // no need for separate file/class
        engine.start();
        System.out.println("Car is moving...");
    }
}
```

👉 Using **inner class** is better when:

* The class (`Engine`) is **closely related** and used only by outer class (`Car`).
* It makes code **compact** and avoids unnecessary **extra classes**.


### Real World Example: Bank Account & Transaction

A **transaction** belongs only to a particular **bank account**.

```java
class BankAccount {
    private double balance = 1000;

    class Transaction { // Member Inner Class
        void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("New Balance: " + balance);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        BankAccount.Transaction tx = account.new Transaction();
        tx.deposit(500);
    }
}
```

👉 `Transaction` is meaningful only inside `BankAccount`.

---

## ✅ (B) Static Inner Class

* Declared as `static`.
* Can be created **without outer object**.
* Can access only **static members** of outer class.

### Example:

```java
class Outer {
    static String msg = "Hello from Outer";

    static class Inner { // Static Inner Class
        void display() {
            System.out.println("Message: " + msg);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Outer.Inner inner = new Outer.Inner(); // no need of outer object
        inner.display();
    }
}
```


### Real World Example: Electronics – Mobile & Battery

A **Battery** can be tested without a **Mobile phone object**.

```java
class Mobile {
    static String brand = "Samsung";

    static class Battery { // Static Inner Class
        void showDetails() {
            System.out.println("Battery belongs to: " + brand);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Mobile.Battery battery = new Mobile.Battery();
        battery.showDetails();
    }
}
```


---

## ✅ (C) Local Inner Class

* Defined **inside a method**.
* Its scope is limited to that method.
* Can access variables of method (if they are `final` or effectively final).

### Example:

```java
class Outer {
    void outerMethod() {
        class LocalInner { // Local Inner Class
            void display() {
                System.out.println("Hello from Local Inner Class");
            }
        }

        LocalInner inner = new LocalInner();
        inner.display();
    }
}

public class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerMethod();
    }
}
```

### Real World Example: School – Teacher giving a test

A **Result** class is only useful **inside the test method**.

```java
class School {
    void conductTest() {
        class Result { // Local Inner Class
            void display() {
                System.out.println("Result: Passed ✅");
            }
        }
        Result r = new Result();
        r.display();
    }
}

public class Main {
    public static void main(String[] args) {
        School school = new School();
        school.conductTest();
    }
}
```

👉 `Result` is **temporary**, valid only during `conductTest()` execution.


---

## ✅ (D) Anonymous Inner Class

* A class **without a name**, created for **one-time use**.
* Mostly used when implementing an **interface** or **abstract class** without writing a separate class.

### Example with Interface:

```java
interface Animal {
    void sound();
}

public class Test {
    public static void main(String[] args) {
        // Anonymous Inner Class implementing interface
        Animal dog = new Animal() {
            public void sound() {
                System.out.println("Dog barks");
            }
        };

        dog.sound();
    }
}
```

### Example with Abstract Class:

```java
abstract class Shape {
    abstract void draw();
}

public class Test {
    public static void main(String[] args) {
        Shape circle = new Shape() { // Anonymous Inner Class
            void draw() {
                System.out.println("Drawing a Circle");
            }
        };

        circle.draw();
    }
}
```


### Real World Example: Button Click Listener (like Android Apps)

You don’t create a separate class for **every button click**. Instead, use an **anonymous class**.

```java
interface ButtonClickListener {
    void onClick();
}

public class Main {
    public static void main(String[] args) {
        // Anonymous Inner Class
        ButtonClickListener button = new ButtonClickListener() {
            public void onClick() {
                System.out.println("Button Clicked! ✅");
            }
        };

        // simulate button click
        button.onClick();
    }
}
```

👉 Used heavily in **GUI programming (Swing, JavaFX, Android)**.


---


# 🔹 Comparison Table of Inner Classes

| Type                | Outer object needed? | Access to outer `private`? | Where defined?     | Real-life Example     |
| ------------------- | -------------------- | -------------------------- | ------------------ | --------------------- |
| **Member Inner**    | ✅ Yes                | ✅ Yes                      | Inside outer class | Car → Engine          |
| **Static Inner**    | ❌ No                 | ❌ Only static              | Inside outer class | Mobile → Battery      |
| **Local Inner**     | ✅ Yes (method scope) | ✅ Yes (final vars only)    | Inside method      | Teacher → Result      |
| **Anonymous Inner** | ✅ Yes                | ✅ Yes                      | Inside method/expr | Button → Click action |
---


# 🎯 Key Takeaways

* **Member Inner Class** → Use when helper class is **tied to an outer object**.
* **Static Inner Class** → Use when helper class is **independent** of outer object.
* **Local Inner Class** → Use when class is **needed only inside one method**.
* **Anonymous Inner Class** → Use for **short, one-time use cases** (event handling, callbacks).
---

# 🎯 Summary

* **Member Inner Class** → Normal inner class, needs outer object, can access all members.
* **Static Inner Class** → Independent of outer object, only accesses static members.
* **Local Inner Class** → Declared inside a method, used locally.
* **Anonymous Inner Class** → Class without name, created for **one-time use** (e.g., interface implementation).

👉 Use **Member Inner Class** when the helper class is **only meaningful inside outer class** (like `Car.Engine`).

---

