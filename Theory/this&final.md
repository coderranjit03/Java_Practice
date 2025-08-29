
---

# 🔹 `this` Keyword

👉 `this` is a **reference variable** that refers to the **current object** of the class.

### Uses of `this`

1. **Differentiate instance variables from local variables**

```java
class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;  // "this.name" = instance variable, "name" = local variable
        this.age = age;
    }

    void display() {
        System.out.println("Name: " + this.name + ", Age: " + this.age);
    }
}
```

2. **Call one constructor from another** (constructor chaining)

```java
class Student {
    String name;
    int age;

    Student() {
        this("Unknown", 0); // calling parameterized constructor
    }

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

3. **Pass current object as argument**

```java
class Test {
    void show(Test obj) {
        System.out.println("Method called using this");
    }

    void call() {
        show(this);  // passing current object
    }
}
```

---

# 🔹 `final` Keyword

`final` is used to make things **unchangeable**.

### 1. Final Variable → Constant

```java
class Test {
    final int MAX_VALUE = 100;

    void change() {
        // MAX_VALUE = 200; ❌ not allowed
    }
}
```

### 2. Final Method → Cannot be overridden

```java
class Parent {
    final void show() {
        System.out.println("This method cannot be overridden");
    }
}

class Child extends Parent {
    // void show() {} ❌ Not allowed
}
```

### 3. Final Class → Cannot be inherited

```java
final class Vehicle {
    void drive() {
        System.out.println("Driving...");
    }
}

// class Car extends Vehicle {} ❌ Not allowed
```

---

# 🔹 `static` Members

👉 Belong to the **class, not the object**.

* Shared by all objects.
* Can be accessed **without creating an object**.

### 1. Static Variable

```java
class Student {
    int id;
    String name;
    static String college = "ABC College"; // shared by all

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println(id + " " + name + " " + college);
    }
}

public class Test {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Ravi");
        Student s2 = new Student(2, "Neha");

        s1.display();
        s2.display();
    }
}
```

➡ Output:

```
1 Ravi ABC College
2 Neha ABC College
```

---

### 2. Static Method

* Can access **static variables directly**.
* Cannot access **non-static (instance) variables** directly.

```java
class Test {
    static int count = 0;

    static void show() {
        System.out.println("Count: " + count);
    }
}

public class Demo {
    public static void main(String[] args) {
        Test.show(); // No object needed
    }
}
```

---

### 3. Static Block

👉 Executes **only once**, when the class is loaded.

```java
class Example {
    static {
        System.out.println("Static block executed");
    }

    public static void main(String[] args) {
        System.out.println("Main method executed");
    }
}
```

➡ Output:

```
Static block executed
Main method executed
```

---

# 🎯 Quick Comparison

| Keyword    | Purpose                      | Example             |
| ---------- | ---------------------------- | ------------------- |
| **this**   | Refers to current object     | `this.name = name;` |
| **final**  | Prevents modification        | `final int x = 10;` |
| **static** | Belongs to class, not object | `static int count;` |

---

