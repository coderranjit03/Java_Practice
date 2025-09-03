

---

# 🟢 Wrapper Classes in Java (Complete Guide)

---

## 1. What are Wrapper Classes?

* In Java, **wrapper classes** are classes that **wrap (contain)** primitive data types (like `int`, `char`, `double`, etc.) into **objects**.
* Primitive types (`int`, `char`, etc.) are **not objects** — they are just simple values stored in memory.
* Sometimes, we need **objects instead of primitives** (like in Collections: `ArrayList`, `HashMap`, etc.), so Java provides **wrapper classes**.

### Primitive → Wrapper Mapping

| Primitive | Wrapper Class |
| --------- | ------------- |
| `byte`    | `Byte`        |
| `short`   | `Short`       |
| `int`     | `Integer`     |
| `long`    | `Long`        |
| `float`   | `Float`       |
| `double`  | `Double`      |
| `char`    | `Character`   |
| `boolean` | `Boolean`     |

---

## 2. Example: Without Wrapper vs With Wrapper

```java
public class WrapperExample {
    public static void main(String[] args) {
        // Primitive type
        int a = 10;

        // Wrapper class
        Integer b = Integer.valueOf(a); // wrapping
        System.out.println("Primitive: " + a);
        System.out.println("Wrapper: " + b);
    }
}
```

✅ Output:

```
Primitive: 10
Wrapper: 10
```

---

## 3. Autoboxing & Unboxing (Basics)

Java automatically converts between **primitive types** and their **wrapper objects**.

### 🔹 Autoboxing (primitive → object)

```java
public class AutoBoxingExample {
    public static void main(String[] args) {
        int num = 5;  // primitive
        Integer obj = num;  // autoboxing (auto-conversion)
        System.out.println("Autoboxing: " + obj);
    }
}
```

### 🔹 Unboxing (object → primitive)

```java
public class AutoUnboxingExample {
    public static void main(String[] args) {
        Integer obj = 20;   // wrapper object
        int num = obj;      // unboxing
        System.out.println("Unboxing: " + num);
    }
}
```

---

# 🔹 Autoboxing and Unboxing in Detail

---

## 4. What is Autoboxing?

* **Definition:**
  Autoboxing is the **automatic conversion** of a **primitive type** (like `int`, `char`, etc.) into its **corresponding wrapper class object** (like `Integer`, `Character`, etc.).
* **Introduced in:** Java 5 (JDK 1.5) — before that, we had to do manual wrapping with `Integer.valueOf()`.
* **Why needed?**

  * Collections like `ArrayList` and `HashMap` only work with **objects**, not primitives.
  * To make coding **simpler** and less verbose.

✅ Example:

```java
int a = 10;
Integer b = a; // Autoboxing (compiler does: Integer.valueOf(a))
```

Behind the scenes:

```java
Integer b = Integer.valueOf(a);
```

---

## 5. What is Unboxing?

* **Definition:**
  Unboxing is the **automatic conversion** of a **wrapper object** into its **corresponding primitive value**.
* **Why needed?**

  * Sometimes we want to do **mathematical operations**, but wrapper objects are not directly usable in arithmetic.
  * Java automatically extracts the primitive for us.

✅ Example:

```java
Integer x = 100;  
int y = x;   // Unboxing (compiler does: x.intValue())
```

Behind the scenes:

```java
int y = x.intValue();
```

---

## 6. Autoboxing & Unboxing in Action

```java
import java.util.ArrayList;

public class AutoBoxingUnboxing {
    public static void main(String[] args) {
        // Autoboxing: primitive → object
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10); // compiler: list.add(Integer.valueOf(10));
        list.add(20);
        list.add(30);

        // Unboxing: object → primitive
        int num = list.get(1); // compiler: list.get(1).intValue();
        System.out.println("Unboxed value: " + num);

        // Autoboxing in expressions
        Integer a = 50;   // primitive 50 → Integer object
        Integer b = 100;
        Integer sum = a + b;  // unboxing a & b → add → boxing result
        System.out.println("Sum: " + sum);
    }
}
```

✅ Output:

```
Unboxed value: 20
Sum: 150
```

---

## 7. Advantages of Autoboxing & Unboxing

✔ Less code → No need to call `Integer.valueOf()` or `.intValue()` manually.
✔ Makes Collections easier to use with numbers, booleans, etc.
✔ Improves **readability** and reduces boilerplate code.

---

## 8. Disadvantages (Important for Interviews 🚨)

* **Performance Overhead:**
  Every time autoboxing/unboxing happens, new objects might be created (except cached values in Integer -128 to 127). This can cause **extra memory and CPU usage**.

* **NullPointerException risk:**
  If a wrapper object is `null` and you unbox it, it throws a `NullPointerException`.

### Example:

```java
public class Pitfalls {
    public static void main(String[] args) {
        Integer obj = null;
        try {
            int x = obj; // Unboxing null → NullPointerException
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
```

✅ Output:

```
Error: java.lang.NullPointerException
```

---

## 9. Essential Methods in Wrapper Classes

Wrapper classes give many **useful methods** that primitives don’t have.

### Example with `Integer`:

```java
public class WrapperMethods {
    public static void main(String[] args) {
        Integer num = 100;

        // Convert to primitive
        int primitive = num.intValue();
        System.out.println("intValue(): " + primitive);

        // Convert to string
        String str = num.toString();
        System.out.println("toString(): " + str);

        // Parse string to int
        int parsed = Integer.parseInt("123");
        System.out.println("parseInt(): " + parsed);

        // Compare two integers
        int result = Integer.compare(10, 20);
        System.out.println("compare(10,20): " + result); // -1 (less than)

        // Max and Min
        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE: " + Integer.MIN_VALUE);
    }
}
```

✅ Output:

```
intValue(): 100
toString(): 100
parseInt(): 123
compare(10,20): -1
MAX_VALUE: 2147483647
MIN_VALUE: -2147483648
```

---

## 10. Real-World Example: Using Wrapper in Collections

Collections like `ArrayList` cannot store **primitive types** directly — only objects. That’s why wrappers are needed.

```java
import java.util.ArrayList;

public class WrapperCollection {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // Autoboxing (primitive → wrapper)
        list.add(10);  
        list.add(20);
        list.add(30);

        // Auto-unboxing (wrapper → primitive)
        int num = list.get(1);  // get 20
        System.out.println("Element at index 1: " + num);
    }
}
```

✅ Output:

```
Element at index 1: 20
```

---

## 11. Real-World Example: Marks Calculation

```java
import java.util.ArrayList;

public class MarksExample {
    public static void main(String[] args) {
        ArrayList<Integer> marks = new ArrayList<>();

        // Autoboxing → primitive to wrapper
        marks.add(90);  
        marks.add(85);
        marks.add(95);

        // Auto-unboxing → wrapper to primitive
        int total = 0;
        for (Integer m : marks) {
            total += m;
        }

        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + (total / marks.size()));
    }
}
```

✅ Output:

```
Total Marks: 270
Average Marks: 90
```

---

## 12. Why Use Wrapper Classes?

✔ Needed in **Collections API** (ArrayList, HashMap, etc.).
✔ Provide **utility methods** (like parsing, conversions, comparisons).
✔ Support **autoboxing & unboxing** for easier coding.
✔ Help when **objects are required instead of primitives** (like in Generics).

---

## 13. Interview Notes (Short Summary)

* **Autoboxing:** primitive → wrapper (automatic).
* **Unboxing:** wrapper → primitive (automatic).
* Introduced in **Java 5**.
* Used mainly in **Collections & Generics**.
* Can cause **NullPointerException** (when unboxing null).
* Has **performance overhead** (object creation).

---

## 14. Simple Words Recap

* Wrapper classes = "boxes" for primitive values → making them behave like objects.
* **Autoboxing** = Java putting your primitive inside a "box".
* **Unboxing** = Java opening the "box" to get the primitive back.

---
