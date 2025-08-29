
---

# 🔹 What is Inheritance?

* **Inheritance** is one of the **4 pillars of OOP (Object-Oriented Programming)**.
* It allows one class (**child / subclass**) to **reuse properties and methods** of another class (**parent / superclass**).
* Achieved using the `extends` keyword.

👉 **Why use Inheritance?**

* Code **reusability** (don’t write same code again and again).
* **Method overriding** (child class provides its own version of parent’s method).
* **Polymorphism** (runtime flexibility).

---

# 🔹 Syntax

```java
class Parent {
    // fields, methods
}

class Child extends Parent {
    // additional fields, methods
}
```

---

# 🔹 Example: Basic Inheritance

```java
class Animal {
    String name;

    void eat() {
        System.out.println(name + " is eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println(name + " is barking 🐶");
    }
}

public class TestInheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.name = "Tommy";
        d.eat();  // from Animal
        d.bark(); // from Dog
    }
}
```

✅ Output:

```
Tommy is eating...
Tommy is barking 🐶
```

👉 Here, `Dog` **inherits** `name` and `eat()` from `Animal`.

---

# 🔹 The `super` Keyword

The `super` keyword is used in inheritance for three things:

1. **Access Parent Class Constructor**

   * If parent has **parameterized constructor**, child must call it using `super(...)`.

   ```java
   class Animal {
       String name;
       Animal(String name) {
           this.name = name;
           System.out.println("Animal constructor called");
       }
   }

   class Dog extends Animal {
       Dog(String name) {
           super(name); // calls Animal constructor
           System.out.println("Dog constructor called");
       }
   }

   public class Test {
       public static void main(String[] args) {
           Dog d = new Dog("Tommy");
       }
   }
   ```

   ✅ Output:

   ```
   Animal constructor called
   Dog constructor called
   ```

---

2. **Call Parent Class Method**

   ```java
   class Animal {
       void sound() {
           System.out.println("Animal makes sound...");
       }
   }

   class Dog extends Animal {
       void sound() {
           super.sound();  // calls parent method
           System.out.println("Dog barks 🐶");
       }
   }

   public class Test {
       public static void main(String[] args) {
           Dog d = new Dog();
           d.sound();
       }
   }
   ```

   ✅ Output:

   ```
   Animal makes sound...
   Dog barks 🐶
   ```

---

3. **Access Parent Class Variable**

   ```java
   class Animal {
       String type = "Animal";
   }

   class Dog extends Animal {
       String type = "Dog";

       void printType() {
           System.out.println(type);       // Dog
           System.out.println(super.type); // Animal
       }
   }

   public class Test {
       public static void main(String[] args) {
           Dog d = new Dog();
           d.printType();
       }
   }
   ```

   ✅ Output:

   ```
   Dog
   Animal
   ```

---

# 🔹 Flow of Constructor Calls

* Always goes **Parent → Child** (top-down).
* If parent has **default constructor**, Java automatically calls it (no need for `super()` explicitly).
* If parent has **parameterized constructor only**, then **child MUST call it explicitly** with `super(args)`.

---

# 🔹 Types of Inheritance in Java

✅ Supported:

1. **Single Inheritance** → one parent, one child.
2. **Multilevel Inheritance** → parent → child → grandchild.
3. **Hierarchical Inheritance** → one parent, multiple children.

❌ Not Supported in Java:

* **Multiple Inheritance with classes** (to avoid ambiguity, but possible with interfaces).

---

# 🔹 Example: Multilevel Inheritance

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Barking..."); }
}

class Puppy extends Dog {
    void weep() { System.out.println("Weeping..."); }
}

public class Test {
    public static void main(String[] args) {
        Puppy p = new Puppy();
        p.eat();  // from Animal
        p.bark(); // from Dog
        p.weep(); // from Puppy
    }
}
```

✅ Output:

```
Eating...
Barking...
Weeping...
```

---

# 📝 Quick Summary

* `extends` → allows inheritance.
* `super` → used to call parent constructor, parent method, parent variable.
* Constructor call flow → always parent → child.
* Java supports → Single, Multilevel, Hierarchical inheritance.
* No multiple inheritance with classes (but possible with interfaces).

---
