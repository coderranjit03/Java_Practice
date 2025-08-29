
---

# 1) Methods: the basics

### What is a method?

A **method** is a named block of code you can call. It can take inputs (**parameters**) and can return a value.

**Syntax**

```java
<access> [non-access] <return-type> <name>(<param-list>) [throws ...] {
    // body
}
```

* **access**: `public`, `protected`, `private`
* **non-access** (optional): `static`, `final`, `abstract`, `synchronized`, etc.
* **return-type**: any type or `void`
* **param-list**: `type name, type name, ...` (supports **varargs**: `type... name`)

### Instance vs Static

```java
class MathUtils {
    // instance method
    int add(int a, int b) { return a + b; }

    // static method (call via class)
    static int mul(int a, int b) { return a * b; }
}

MathUtils m = new MathUtils();
m.add(2,3);              // instance call
MathUtils.mul(2,3);      // static call
```

### Parameters & Return

* Java is **pass-by-value** always.

  * For primitives → the value is copied.
  * For objects → the **reference value** is copied (so you can mutate the same object inside the method, but you can’t rebind the caller’s variable).

```java
class Box { int x; }
void change(Box b) { b.x = 99; }   // mutates the object
void swap(int a, int b) { int t=a; a=b; b=t; } // caller's ints unchanged
```

### Varargs (variable arguments)

```java
int sum(int... nums) {   // nums is an int[]
    int s = 0;
    for (int n : nums) s += n;
    return s;
}

sum();          // 0
sum(1,2,3,4);   // 10
```

---

# 2) Method Signature (important for overloading)

A method’s **signature** = **name + parameter types/order**.
**Not** part of signature: return type, access modifier, `throws` list, `static/final`.

* These two **conflict** (same signature):

  ```java
  int f(int x) { ... }
  void f(int x) { ... }     // ❌ compile error: only return type differs
  ```

---

# 3) Method Overloading

### What is it?

**Same method name**, **different parameter lists** (count, types, or order) **in the same class** (or between class & subclass). Chosen at **compile-time** (static binding).

### Valid overloading patterns

```java
void print();                // different count
void print(int a);
void print(int a, int b);

void show(int a);            // different types
void show(double a);

void log(int a, String s);   // different order
void log(String s, int a);
```

### You **cannot** overload by only changing:

* Return type
* Access modifier
* `throws` exceptions

---

## Resolution rules (who gets picked?)

When you call an overloaded method, the compiler chooses using this priority:

**Exact match** → **widening** (e.g., `int`→`long`) → **boxing** (`int`→`Integer`) → **varargs**

```java
void m(long x) { System.out.println("long"); }
void m(Integer x) { System.out.println("Integer"); }
void m(int... x) { System.out.println("varargs"); }

m(5);  // prints "long" (int widens to long, preferred over boxing & varargs)
```

More examples:

```java
void p(double x) { System.out.println("double"); }
void p(Float x)  { System.out.println("Float"); }

p(3.14f); // exact primitive is float, but there is no p(float)
          // choices: widen float→double OR box to Float
          // Widening beats boxing → prints "double"
```

### Ambiguity cases (compile error)

```java
void t(Integer x, Long y) {}
void t(Long x, Integer y) {}
// t(1, 1); // ❌ ambiguous: both require boxing in different ways
```

### Varargs pitfalls

```java
void q(int a, int b) { System.out.println("fixed"); }
void q(int... a)     { System.out.println("varargs"); }

q(1,2); // "fixed" wins over varargs
q(1);   // only varargs fits → "varargs"
```

### Autoboxing & overloading

```java
void r(Integer x) { System.out.println("Integer"); }
void r(Object x)  { System.out.println("Object"); }

r(10);        // int → Integer (boxing) is better than int → Object (widen to Object not allowed for primitive)
               // prints "Integer"
```

---

# 4) Overloading + Inheritance (gotchas)

A subclass can **add new overloads**. Overload **resolution** happens at **compile-time** using the **reference type**, then normal **overriding** (if applicable) happens at runtime.

```java
class Base {
    void m(Number n) { System.out.println("Base.Number"); }
}

class Sub extends Base {
    void m(Integer i) { System.out.println("Sub.Integer"); } // overload
    @Override
    void m(Number n)  { System.out.println("Sub.Number"); }  // override
}

Base b = new Sub();
b.m(5);         // reference type is Base → only m(Number) visible at compile time
                // chosen: m(Number), then overridden at runtime → prints "Sub.Number"

Sub s = new Sub();
s.m(5);         // both m(Integer) and m(Number) visible
                // most specific match wins → prints "Sub.Integer"
```

---

# 5) Overloading vs Overriding (interview table)

| Aspect           | Overloading                            | Overriding                           |
| ---------------- | -------------------------------------- | ------------------------------------ |
| Where            | Same class (or subclass adds overload) | Subclass only                        |
| Signature        | **Different** parameter list           | **Same** signature                   |
| Binding          | **Compile-time**                       | **Runtime** (dynamic dispatch)       |
| Return type      | Can differ                             | Can be covariant (same or subtype)   |
| Access level     | Can change freely                      | Can’t be more restrictive            |
| `static` methods | Can be overloaded                      | Can’t be overridden (they’re hidden) |

---

# 6) Best practices

* Keep overloads **unambiguous**; avoid overload sets that differ only by subtle boxing/widening.
* Prefer **most specific** types for common calls, add a **varargs** fallback.
* Document behavior differences between overloads; don’t surprise the caller.
* For APIs, consider **builders** when too many overloads make call sites confusing.

---

# 7) Quick, runnable demo (all-in-one)

```java
class OverloadDemo {

    // Overloads
    static void print()                 { System.out.println("print()"); }
    static void print(int x)            { System.out.println("print(int)"); }
    static void print(long x)           { System.out.println("print(long)"); }
    static void print(Integer x)        { System.out.println("print(Integer)"); }
    static void print(double x)         { System.out.println("print(double)"); }
    static void print(int... xs)        { System.out.println("print(varargs)"); }

    public static void main(String[] args) {
        print();            // exact → print()
        print(5);           // exact int? candidates: int, long, Integer, double, varargs
                            // exact int not defined → prefers widening to long over boxing & varargs → print(long)
        print(5L);          // print(long)
        print(Integer.valueOf(5)); // print(Integer)
        print(3.14);        // print(double)
        print(1,2,3);       // fixed-arity not available → varargs → print(varargs)

        // Ambiguity example (commented to keep compiling):
        // void a(Integer x, Long y) {}  // imagine these existed
        // void a(Long x, Integer y) {}
        // a(1, 1); // ❌ ambiguous
    }
}
```

---

## TL;DR

* **Method** = named block with inputs (params) and an output (return).
* Java is **pass-by-value**; objects are mutated via copied reference.
* **Overloading** = same name, different parameters; resolved at **compile time**.
* Resolution order: **exact > widening > boxing > varargs**.
* With inheritance, compile-time chooses which **overload** applies based on **reference type**, then runtime does **overriding** (if applicable).


