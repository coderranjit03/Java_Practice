

# 1️⃣ **Class in Java**

* A **class** is a **blueprint/template** to create objects.
* It groups **variables (fields/properties)** and **methods (functions)** into one unit.
* It defines **what an object will have (data)** and **what it can do (behavior)**.

👉 Think of a **class** like a blueprint of a house:
The blueprint describes rooms, doors, windows — but it's not a house until you build (object) from it.

✅ Example:

```java
class Car {
    // Fields (variables / properties)
    String brand;
    int speed;

    // Methods (behavior)
    void drive() {
        System.out.println(brand + " is driving at " + speed + " km/h");
    }
}
```

---

# 2️⃣ **Object in Java**

* An **object** is a **real-world instance** of a class.
* It’s created using the `new` keyword.
* Each object has its **own copy** of instance variables, but shares the same class methods.

👉 Example:

```java
public class Main {
    public static void main(String[] args) {
        // Creating objects of Car
        Car car1 = new Car();   // Object 1
        Car car2 = new Car();   // Object 2

        // Assign values to object fields
        car1.brand = "Tesla";
        car1.speed = 120;

        car2.brand = "BMW";
        car2.speed = 150;

        // Calling methods
        car1.drive();
        car2.drive();
    }
}
```

✅ Output:

```
Tesla is driving at 120 km/h
BMW is driving at 150 km/h
```

👉 Here:

* `Car` = class (blueprint).
* `car1`, `car2` = objects (instances).

---

# 3️⃣ **Constructors in Java**

* A **constructor** is a **special method** used to initialize objects.
* Same name as the class.
* No return type (not even `void`).
* Called **automatically** when an object is created using `new`.

---

## Types of Constructors:

### 🔹 (a) **Default Constructor**

* If you don’t write any constructor, Java provides one automatically.
* It initializes instance variables with **default values** (`0`, `null`, `false`).

✅ Example:

```java
class Student {
    String name;
    int age;
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // default constructor is called
        System.out.println(s1.name); // null
        System.out.println(s1.age);  // 0
    }
}
```

---

### 🔹 (b) **No-argument Constructor (User-defined)**

* You can create your own constructor without parameters.

✅ Example:

```java
class Student {
    String name;
    int age;

    // No-argument constructor
    Student() {
        name = "Unknown";
        age = 18;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(); // calls no-arg constructor
        System.out.println(s1.name + " - " + s1.age);
    }
}
```

✅ Output:

```
Unknown - 18
```

---

### 🔹 (c) **Parameterized Constructor**

* A constructor that accepts arguments to set values while creating the object.

✅ Example:

```java
class Student {
    String name;
    int age;

    // Parameterized constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Rohan", 20);
        Student s2 = new Student("Priya", 22);

        System.out.println(s1.name + " - " + s1.age);
        System.out.println(s2.name + " - " + s2.age);
    }
}
```

✅ Output:

```
Rohan - 20
Priya - 22
```

---

### 🔹 (d) **Constructor Overloading**

* You can have multiple constructors with different parameter lists.
* This is called **constructor overloading**.

✅ Example:

```java
class Student {
    String name;
    int age;

    // No-arg constructor
    Student() {
        name = "Unknown";
        age = 18;
    }

    // Parameterized constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();            // calls no-arg
        Student s2 = new Student("Aman", 21);  // calls parameterized

        System.out.println(s1.name + " - " + s1.age);
        System.out.println(s2.name + " - " + s2.age);
    }
}
```

✅ Output:

```
Unknown - 18
Aman - 21
```

---

# 4️⃣ **Key Points about Constructors**

1. **Same name as class**.
2. **No return type**.
3. **Called automatically** when object is created.
4. Can be **overloaded**.
5. If you define your own constructor → Java **does not** provide a default one.
6. Can call one constructor from another using `this()`.
7. Can call parent constructor using `super()`.

---

# 5️⃣ **Real-World Example**

```java
class BankAccount {
    String accountHolder;
    double balance;

    // No-arg constructor
    BankAccount() {
        accountHolder = "Unknown";
        balance = 0.0;
    }

    // Parameterized constructor
    BankAccount(String name, double bal) {
        accountHolder = name;
        balance = bal;
    }

    void display() {
        System.out.println(accountHolder + " has balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount(); // No-arg constructor
        BankAccount a2 = new BankAccount("Ravi", 5000.0); // Parameterized

        a1.display();
        a2.display();
    }
}
```

✅ Output:

```
Unknown has balance: 0.0
Ravi has balance: 5000.0
```

---

👉 So in short:

* **Class = blueprint**
* **Object = real-world instance**
* **Constructor = special method that initializes objects**

---
