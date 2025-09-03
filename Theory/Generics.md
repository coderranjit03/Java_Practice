

# Java Generics Deep Dive

## Table of Contents

- [Java Generics Deep Dive](#java-generics-deep-dive)
  - [Table of Contents](#table-of-contents)
  - [1. Introduction to Generics](#1-introduction-to-generics)
  - [2. Generic Classes \& Type Parameters](#2-generic-classes--type-parameters)
  - [3. Generic Methods \& Overloading](#3-generic-methods--overloading)
  - [4. Type Erasure](#4-type-erasure)
  - [5. Bounded Type Parameters](#5-bounded-type-parameters)
  - [6. Wildcards \& PECS Principle](#6-wildcards--pecs-principle)
  - [7. Upper-Bounded Wildcards (`? extends T`)](#7-upper-bounded-wildcards--extends-t)
      - [Visual Diagram (Covariance):](#visual-diagram-covariance)
  - [8. Lower-Bounded Wildcards (`? super T`)](#8-lower-bounded-wildcards--super-t)
      - [Visual Diagram (Contravariance):](#visual-diagram-contravariance)
  - [9. Raw Types in Generics](#9-raw-types-in-generics)
  - [10. Generics in Exceptions](#10-generics-in-exceptions)
  - [11. Intersection Types (`&`)](#11-intersection-types-)
  - [12. Best Practices](#12-best-practices)
  - [13. Summary Tables \& Diagrams](#13-summary-tables--diagrams)
    - [Wildcards Comparison](#wildcards-comparison)
    - [PECS Diagram](#pecs-diagram)
    - [Type Erasure Diagram](#type-erasure-diagram)
    - [Further Details?](#further-details)

---

## 1. Introduction to Generics

Before generics:

```java
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add(123);
        list.add(3.14);

        String str = (String) list.get(0);
        String str1 = (String) list.get(1);
    }
}
```

Exception:

```
java.lang.ClassCastException: Integer cannot be cast to String
```

**Issues:**

* No type safety
* Manual casting
* No compile-time checks

Generics solve these.

---

## 2. Generic Classes & Type Parameters

```java
public class Box {
    private Object value;
    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }
}

public class Main {
    public static void main(String[] args) {
        Box box = new Box();
        box.setValue(1);
        String i = (String) box.getValue(); // EXCEPTION !!!
        System.out.println(i);
    }
}
```

Generic version:

```java
public class Box<T> {
    private T value;
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.setValue(1);
        Integer i = box.getValue();
        System.out.println(i);
    }
}
```

**Concept:** `T` is replaced at compile time for type safety.

---

## 3. Generic Methods & Overloading

```java
public class GenericMethodExample {
    public <T> void display(T element) {
        System.out.println("Generic display: " + element);
    }

    public void display(Integer element) {
        System.out.println("Integer display: " + element);
    }

    public static void main(String[] args) {
        GenericMethodExample example = new GenericMethodExample();
        example.display(42);         // Calls the Integer display method
        example.display("Generics"); // Calls the generic display method
    }
}
```

Even with a generic method, a non-generic overload is preferred when types match exactly.

---

## 4. Type Erasure

How generics work under the hood:

```java
public class Box<T> {
    private T item;
    public void setItem(T item) { this.item = item; }
    public T getItem() { return item; }
}

public class Main {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello, Generics!");
        String item = stringBox.getItem();
        System.out.println(item);
    }
}
```

**After compilation (erased):**

```java
public class Box {
    private Object item;
    public void setItem(Object item) { this.item = item; }
    public Object getItem() { return item; }
}
```

At runtime, generics are gone; casts are implicitly inserted.

---

## 5. Bounded Type Parameters

```java
class GenericClass<T extends Number> {
    private T value;
    public GenericClass(T value) { this.value = value; }
    public void display() { System.out.println("Value: " + value); }
}

public class Main {
    public static void main(String[] args) {
        GenericClass<Integer> intObj = new GenericClass<>(123);
        intObj.display();

        GenericClass<Double> doubleObj = new GenericClass<>(45.67);
        doubleObj.display();

        // GenericClass<String> strObj = new GenericClass<>("Hello"); // Error
    }
}
```

Multiple bounds:

```java
interface Printable { void print(); }
class MyNumber extends Number implements Printable {
    private final int value;
    public MyNumber(int value) { this.value = value; }
    public void print() { System.out.println("MyNumber: " + value); }
    public int intValue() { return value; }
    public long longValue() { return value; }
    public float floatValue() { return value; }
    public double doubleValue() { return value; }
}

class Boxx<T extends Number & Printable> {
    private T item;
    public Boxx(T item) { this.item = item; }
    public void display() { item.print(); }
}

public class Test {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber(12);
        Boxx<MyNumber> box = new Boxx<>(myNumber);
        box.display();
    }
}
```

Class bound must come before interface bounds.

---

## 6. Wildcards & PECS Principle

**Producer extends, Consumer super (PECS)**

**Producer** (`? extends T`): read data
**Consumer** (`? super T`): write data

```java
public void copy(List<? extends Number> source, List<? super Number> destination) {
    for (Number n : source) {
        destination.add(n);
    }
}
```

---

## 7. Upper-Bounded Wildcards (`? extends T`)

```java
public void printList(List<? extends Number> list) {
    for (Number n : list) {
        System.out.println(n);
    }
}
```

#### Visual Diagram (Covariance):

```
Number
  ↑
Integer, Double, Float, ...
```

You **can read** elements safely as `Number`, but you **cannot add elements** (except `null`).

---

## 8. Lower-Bounded Wildcards (`? super T`)

```java
public static void processMixedTypes(List<? super Integer> list) {
    list.add(100);
    list.add(200);
    Object obj = list.get(0);  // Only safe operation
}

public static void main(String[] args) {
    List<Object> objects = new ArrayList<>();
    processMixedTypes(objects);

    List<Number> numbers = new ArrayList<>();
    processMixedTypes(numbers);
}
```

#### Visual Diagram (Contravariance):

```
Object
  ↑
Number
  ↑
Integer
```

You **can add** `Integer`, but can only retrieve as `Object`.

---

## 9. Raw Types in Generics

```java
List list = new ArrayList();  // Raw type
list.add("Hello");
list.add(123);
```

Unchecked and unsafe.
**Prefer:**

```java
List<String> list = new ArrayList<>();
// list.add(123); // Compile-time error
```

---

## 10. Generics in Exceptions

Cannot create generic exceptions directly:

```java
// class MyGenericException<T> extends Exception { } // Error
```

Use generic fields instead:

```java
class DetailedException<T> extends Exception {
    private T details;
    public DetailedException(String message, T details) {
        super(message);
        this.details = details;
    }
    public T getDetails() { return details; }
}

public class Main {
    public static void main(String[] args) {
        try {
            throw new DetailedException<>("Error occurred", 404);
        } catch (DetailedException<Integer> e) {
            System.out.println(e.getDetails());
        }
    }
}
```

---

## 11. Intersection Types (`&`)

```java
interface Printer { void print(); }
interface Scanner { void scan(); }

class AllInOne implements Printer, Scanner {
    public void print() { System.out.println("Printing..."); }
    public void scan() { System.out.println("Scanning..."); }
}

public class IntersectionTypeExample {
    public static <T extends Printer & Scanner> void useDevice(T device) {
        device.print();
        device.scan();
    }
    public static void main(String[] args) {
        AllInOne machine = new AllInOne();
        useDevice(machine);
    }
}
```

---

## 12. Best Practices

| Guideline                              | Description                      |
| -------------------------------------- | -------------------------------- |
| Use generics for type safety           | Avoid ClassCastException         |
| Avoid raw types                        | Always specify type arguments    |
| Use `? extends T` for producers        | Safe read-only access            |
| Use `? super T` for consumers          | Safe write-only access           |
| Follow PECS                            | Producer Extends, Consumer Super |
| Keep wildcard bounds simple            | Maintain readability             |
| Use intersection types for flexibility | Combine multiple constraints     |
| Remember type erasure                  | No runtime generic type info     |

---

## 13. Summary Tables & Diagrams

### Wildcards Comparison

| Wildcard      | Use Case     | Can Add? | Can Read?   |
| ------------- | ------------ | -------- | ----------- |
| `?`           | Unknown type | No       | As `Object` |
| `? extends T` | Producer     | No       | As `T`      |
| `? super T`   | Consumer     | Yes      | As `Object` |

### PECS Diagram

```
Producer → Extends (Read-only)
Consumer → Super   (Write-only)
```

### Type Erasure Diagram

```
List<String>, List<Integer>
       ↓ (compile-time)
List<Object>
(runtime)
```
---

### Further Details?

For deep dives, visual aids, and interview-ready explanations, check out the full article: **Engineering Digest – Generics** ([Medium][1]).

---



[1]: https://engineeringdigest.medium.com/generics-b158a743d18f?utm_source=chatgpt.com "Generics. Without Generics | by Engineering Digest"
