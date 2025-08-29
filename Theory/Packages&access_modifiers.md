
---

# 🔹 1. What are Packages in Java?

A **package** in Java is like a **folder** in your computer.
It is used to organize classes, interfaces, and sub-packages into a **namespace**.

👉 Think of it like:

* Your computer → folders
* Java project → packages

### ✅ Types of Packages:

1. **Built-in packages** → Provided by Java (e.g. `java.util`, `java.io`, `java.sql`)
2. **User-defined packages** → Created by developers

---

### ✅ Example: Built-in Package

```java
import java.util.ArrayList;   // importing built-in package

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        System.out.println(list);
    }
}
```

---

### ✅ Example: User-defined Package

**Step 1: Create a package**

```java
package mypackage; // declare package

public class MyClass {
    public void display() {
        System.out.println("Hello from MyClass in mypackage");
    }
}
```

**Step 2: Use the package in another file**

```java
import mypackage.MyClass;

public class Test {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.display();
    }
}
```

👉 Benefits of Packages:

* Avoids **naming conflicts**
* Provides **reusability**
* Organizes code properly
* Provides **access control** (with modifiers)

---

# 🔹 2. Access Modifiers in Java

Access modifiers control the **visibility (scope)** of classes, methods, and variables.

| Modifier                 | Class | Package | Subclass (other pkg) | World (anywhere) |
| ------------------------ | ----- | ------- | -------------------- | ---------------- |
| **public**               | ✅ Yes | ✅ Yes   | ✅ Yes                | ✅ Yes            |
| **protected**            | ✅ Yes | ✅ Yes   | ✅ Yes                | ❌ No             |
| **default** (no keyword) | ✅ Yes | ✅ Yes   | ❌ No                 | ❌ No             |
| **private**              | ✅ Yes | ❌ No    | ❌ No                 | ❌ No             |

---

### ✅ Examples:

#### 1. `public`

```java
public class A {
    public void show() {
        System.out.println("Public method");
    }
}
```

✔ Can be accessed **from anywhere**.

---

#### 2. `private`

```java
class A {
    private void show() {
        System.out.println("Private method");
    }
    public void callShow() {
        show();  // accessible inside same class
    }
}

public class Test {
    public static void main(String[] args) {
        A obj = new A();
        // obj.show(); ❌ Not allowed (private)
        obj.callShow(); // ✅ Allowed (through public method)
    }
}
```

✔ Accessible **only inside the same class**.

---

#### 3. `default` (no modifier)

```java
class A {
    void show() {  // default access
        System.out.println("Default method");
    }
}

public class Test {
    public static void main(String[] args) {
        A obj = new A();
        obj.show(); // ✅ Works inside same package
    }
}
```

❌ Not accessible outside the **package**.

---

#### 4. `protected`

```java
package pack1;

public class A {
    protected void show() {
        System.out.println("Protected method");
    }
}
```

```java
package pack2;
import pack1.A;

class B extends A {
    public void test() {
        show(); // ✅ Accessible in subclass (different package)
    }
}

public class Test {
    public static void main(String[] args) {
        B obj = new B();
        obj.test();
        // obj.show(); ❌ Not directly accessible outside
    }
}
```

✔ Accessible in **subclasses**, even if they are in **different packages**.

---

# 🎯 Key Takeaways

* **Packages** = way to organize code (like folders).
* **Access modifiers** control visibility:

  * `public` → anywhere
  * `protected` → package + subclass
  * `default` → package only
  * `private` → class only

---
