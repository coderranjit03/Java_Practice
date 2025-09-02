# Exception Handling in Java — Complete Guide

---

## 🚨 1. What is an Exception in Java?

An **exception** is an **unwanted or unexpected event** that occurs during program execution and disrupts the normal flow of instructions.

**Examples:**

* Dividing by 0 → `ArithmeticException`
* Accessing an invalid array index → `ArrayIndexOutOfBoundsException`
* Trying to open a file that doesn’t exist → `FileNotFoundException`

Java provides a **robust mechanism** to handle such errors → called **Exception Handling**.

---

## 🎯 2. Why Exception Handling?

* Prevent the program from crashing unexpectedly.
* Provide meaningful error messages instead of raw error traces.
* Maintain **normal program flow** after handling errors.
* Encourage developers to **anticipate and handle errors gracefully**.

**Without Exception Handling:**

```java
public class Demo {
    public static void main(String[] args) {
        int a = 10, b = 0;
        int result = a / b; // Program crashes here
        System.out.println("Result: " + result);
    }
}
```

**With Exception Handling:**

```java
public class Demo {
    public static void main(String[] args) {
        int a = 10, b = 0;
        try {
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        }
        System.out.println("Program continues...");
    }
}
```

---

## 🧩 3. Types of Exceptions

Java exceptions are divided into two main categories:

### (a) Checked Exceptions

* Checked **at compile-time**.
* If not handled, the program will not compile.
* Examples: `IOException`, `SQLException`, `FileNotFoundException`.

```java
import java.io.*;

public class CheckedExample {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("abc.txt"); // File may not exist
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
```

---

### (b) Unchecked Exceptions

* Occur at **runtime**.
* Not checked by the compiler.
* Examples: `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`.

```java
public class UncheckedExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        try {
            System.out.println(arr[5]); // Invalid index
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index is invalid!");
        }
    }
}
```

---

## 🔑 4. Exception Handling Keywords

* **try** — Block of code that might throw exceptions.
* **catch** — Block that handles the exception.
* **finally** — Block that executes **always** (cleanup code), regardless of exception.
* **throw** — Used to explicitly throw an exception.
* **throws** — Declares exceptions a method might throw.

---

## 🛠️ 5. try-catch-finally Example

```java
public class TryCatchFinally {
    public static void main(String[] args) {
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block always executes!");
        }
    }
}
```

**Output:**

```
Exception caught: / by zero
Finally block always executes!
```

---

## 🏗️ 6. Creating Custom Exceptions

Sometimes built-in exceptions are not enough. Create custom exceptions for domain-specific errors.

**Checked custom exception (extends Exception):**

```java
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class CustomExceptionExample {
    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18 to vote.");
        } else {
            System.out.println("You are eligible to vote!");
        }
    }

    public static void main(String[] args) {
        try {
            checkAge(15);
        } catch (InvalidAgeException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}
```

**Output:**

```
Caught Exception: Age must be at least 18 to vote.
```

---

## 🌍 7. Real-World Examples of Exception Handling

* **Banking System** — throw `InsufficientBalanceException` when balance is insufficient.
* **File Handling System** — `FileNotFoundException` when a file is missing.
* **Web Applications** — `InvalidUserException` for bad login attempts.
* **E-commerce Website** — `OutOfStockException` when an item is unavailable.

---

## 🎯 8. Key Takeaways

* **Exceptions** = unexpected events at runtime.
* Two types → **Checked** (compile-time) & **Unchecked** (runtime).
* **try-catch-finally** is the core mechanism to handle exceptions.
* Use **custom exceptions** for domain-specific error handling.
* Exception handling makes programs **robust, user-friendly, and safe**.

---

## 🔄 9. Try-With-Resources (Introduced in Java 7)

### 📖 What is it?

A **try-with-resources** block automatically **closes resources** (files, DB connections, sockets, streams) that implement `AutoCloseable`. No need for manual `finally` cleanup.

### 🔑 Why use it?

* Cleaner code
* Automatic resource closing
* Less error-prone

### 📌 Traditional Way (Before Java 7)

```java
import java.io.*;

public class OldWay {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close(); // must close manually
                }
            } catch (IOException e) {
                System.out.println("Error closing resource!");
            }
        }
    }
}
```

### 📌 Modern Way (Try-With-Resources)

```java
import java.io.*;

public class TryWithResources {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        // br is closed automatically
    }
}
```

**Benefits:** Cleaner code, automatic closing, safer resource management.

---

## 🏗️ Custom Resource with AutoCloseable

```java
class MyResource implements AutoCloseable {
    public void useResource() {
        System.out.println("Using resource...");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically!");
    }
}

public class CustomResourceExample {
    public static void main(String[] args) {
        try (MyResource res = new MyResource()) {
            res.useResource();
        }
    }
}
```

**Output:**

```
Using resource...
Resource closed automatically!
```

---

## 🌍 Real-World Uses for Try-With-Resources

* File handling (read/write)
* Database connections (JDBC `Connection`, `Statement`, `ResultSet`)
* Network I/O (sockets, streams)

---

## 🎯 10. Complete Picture of Exception Handling in Java

1. **try-catch-finally** → handle exceptions + manual cleanup.
2. **try-with-resources** → automatic cleanup (preferred for resources).
3. **Checked Exceptions** → compile-time enforced.
4. **Unchecked Exceptions** → runtime, programmer errors.
5. **Custom Exceptions** → domain-specific error types.

---

✅ With this, you now have a fully formatted, comprehensive guide to **Exception Handling in Java**, including **try-with-resources**.

---
