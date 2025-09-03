
---

# 🔹 What is an Enum?

* `enum` in Java is a **special data type** used to represent a group of **fixed constants** (unchangeable values).
* Example: Directions (NORTH, SOUTH, EAST, WEST), Days of the week, Status codes, etc.

👉 In other words, **enum is like a class**, but instead of creating objects, it defines **named constants**.

---

# 🔹 Basic Syntax

```java
enum Direction {
    NORTH, SOUTH, EAST, WEST
}

public class EnumExample {
    public static void main(String[] args) {
        Direction dir = Direction.NORTH;
        System.out.println("Direction: " + dir);
    }
}
```

### ✅ Output:

```
Direction: NORTH
```

---

# 🔹 Key Features of Enums

1. **Enums are constants** (implicitly `public static final`).

   * Example: `Direction.NORTH` is like a constant.
2. **Enums are type-safe** – you cannot assign other values.

   ```java
   Direction d = Direction.NORTH;  // ✅ valid
   // Direction d = "north";       // ❌ invalid
   ```
3. **Enums are classes** internally → they can have:

   * constructors
   * methods
   * fields
   * even implement interfaces

---

# 🔹 Enum with Switch

Enums are commonly used with **switch**:

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class EnumSwitchExample {
    public static void main(String[] args) {
        Day today = Day.SATURDAY;

        switch (today) {
            case MONDAY:
                System.out.println("Start of the week!");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Weekend vibes!");
                break;
            default:
                System.out.println("Midweek grind!");
        }
    }
}
```

### ✅ Output:

```
Weekend vibes!
```

---

# 🔹 Enum with Fields, Constructors & Methods

Enums can also store data and define behavior.

```java
enum Status {
    SUCCESS(200), ERROR(500), NOT_FOUND(404);

    private int code; // field

    // Constructor
    Status(int code) {
        this.code = code;
    }

    // Getter
    public int getCode() {
        return code;
    }
}

public class EnumWithFields {
    public static void main(String[] args) {
        Status s = Status.ERROR;
        System.out.println("Status: " + s + ", Code: " + s.getCode());
    }
}
```

### ✅ Output:

```
Status: ERROR, Code: 500
```

---

# 🔹 Enum Methods

Enums come with some useful built-in methods:

```java
enum Color { RED, GREEN, BLUE }

public class EnumMethods {
    public static void main(String[] args) {
        // values() → returns array of constants
        for (Color c : Color.values()) {
            System.out.println(c);
        }

        // valueOf() → convert String to enum
        Color c1 = Color.valueOf("RED");
        System.out.println("Color: " + c1);

        // ordinal() → index (0-based)
        System.out.println("Index of BLUE: " + Color.BLUE.ordinal());
    }
}
```

### ✅ Output:

```
RED
GREEN
BLUE
Color: RED
Index of BLUE: 2
```

---

# 🔹 Real-World Examples

1. **Days of the Week**

   ```java
   enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
   ```

2. **HTTP Response Status**

   ```java
   enum HttpStatus {
       OK(200), BAD_REQUEST(400), UNAUTHORIZED(401), NOT_FOUND(404);
       private int code;
       HttpStatus(int code) { this.code = code; }
       public int getCode() { return code; }
   }
   ```

3. **Payment Mode**

   ```java
   enum PaymentMode { CASH, CARD, UPI, NET_BANKING }
   ```

---

# 🔹 Advantages of Enums

* Prevents using invalid values (type-safety).
* Improves code readability & maintainability.
* Replaces multiple `static final` constants with a cleaner approach.
* Can have fields, methods, and constructors for powerful use cases.

---

✅ **In short:**

* Enum = special class to represent **fixed constants**.
* It’s **type-safe, readable, and powerful**.
* Can be used with **switch, fields, methods**, and is widely used in **real-world applications** (HTTP codes, directions, payment methods, states, etc).



---

## ✅ Differences Between **Enum vs Class vs Interface**

| Feature              | Enum                                                                           | Class                                                         | Interface                                                                           |
| -------------------- | ------------------------------------------------------------------------------ | ------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| **Purpose**          | Represents a group of **constants**                                            | Blueprint for creating **objects**                            | Defines **contracts/behaviors** (methods without implementation)                    |
| **Instantiation**    | Cannot create objects using `new` (e.g., `new Direction()` ❌)                  | Can create objects with `new`                                 | Cannot instantiate directly                                                         |
| **Constants**        | Provides **predefined, type-safe constants**                                   | Constants are usually defined as `public static final` fields | Constants are implicitly `public static final` if defined                           |
| **Inheritance**      | All enums **implicitly extend `java.lang.Enum`** (cannot extend other classes) | Can extend another class (single inheritance)                 | Can extend multiple interfaces                                                      |
| **Methods & Fields** | Can have fields, constructors, and methods                                     | Can have fields, constructors, and methods                    | Can only have **abstract methods** (Java 7) or **default/static methods** (Java 8+) |
| **Type-Safety**      | Enums are **type-safe** (only valid constants allowed)                         | Classes don’t enforce type-safety for constants               | Interfaces don’t enforce type-safety for constants                                  |
| **Switch Case**      | Can be used directly in `switch` statements                                    | Normal classes cannot                                         | Interfaces cannot be used directly                                                  |

---

## ✅ Example: Enum vs Class

### Using `class` with constants

```java
class DirectionClass {
    public static final String NORTH = "NORTH";
    public static final String SOUTH = "SOUTH";
}
public class ClassExample {
    public static void main(String[] args) {
        String dir = DirectionClass.NORTH;
        System.out.println("Direction: " + dir);

        dir = "EAST"; // ❌ No restriction, invalid value also possible
    }
}
```

👉 Problem: `"EAST"` string is not prevented — **not type-safe**.

---

### Using `enum`

```java
enum DirectionEnum {
    NORTH, SOUTH, EAST, WEST;
}

public class EnumExample {
    public static void main(String[] args) {
        DirectionEnum dir = DirectionEnum.NORTH;
        System.out.println("Direction: " + dir);

        // dir = "EAST"; // ❌ Not allowed, must be DirectionEnum.EAST
        dir = DirectionEnum.EAST; // ✅ Type-safe
    }
}
```

👉 Advantage: **Only valid directions are allowed**.

---

## ✅ Example: Enum vs Interface

### Using `interface` constants

```java
interface Colors {
    String RED = "RED";
    String GREEN = "GREEN";
    String BLUE = "BLUE";
}

public class InterfaceExample {
    public static void main(String[] args) {
        String c = Colors.RED;
        System.out.println("Color: " + c);

        c = "YELLOW"; // ❌ No restriction
    }
}
```

### Using `enum`

```java
enum ColorEnum {
    RED, GREEN, BLUE;
}

public class EnumVsInterface {
    public static void main(String[] args) {
        ColorEnum c = ColorEnum.RED;
        System.out.println("Color: " + c);

        // c = "YELLOW"; ❌ Not allowed
        c = ColorEnum.GREEN; // ✅ Allowed only if defined in enum
    }
}
```

---

## ✅ Real-World Analogy

1. **Enum is like a restaurant menu** → You can only choose from fixed items (Pizza, Burger, Pasta).
2. **Class is like a recipe book** → You can create objects (dishes) as per the blueprint.
3. **Interface is like a contract** → It only tells *what needs to be cooked*, but not *how*.

---

## ✅ Key Takeaways

* **Enum vs Class** → Enum is **better for constants** because it ensures **type-safety** and prevents invalid values.
* **Enum vs Interface** → Enum can hold values and behaviors, while an interface is only a **contract**.
* Enums are internally like **final classes** that extend `java.lang.Enum`.

---

👉 So, in real-world coding:

* Use **class** → when you need to create objects.
* Use **interface** → when you want to define a contract.
* Use **enum** → when you want to represent a fixed set of values.

---

---

## 🔎 1. Enums Are Special Classes

* In Java, when you write an `enum`, the compiler **converts it into a class** that:

  * **extends `java.lang.Enum`**
  * has a **fixed set of `public static final` objects** (instances of that class).
* That’s why enums are **type-safe**: only those predefined objects exist.

---

## 🔎 2. Example Enum

```java
enum Colors {
    RED, GREEN, BLUE;
}
```

---

## 🔎 3. What Compiler Generates Internally

The compiler translates the above into something like:

```java
final class Colors extends java.lang.Enum<Colors> {
    // Instances (enum constants)
    public static final Colors RED = new Colors("RED", 0);
    public static final Colors GREEN = new Colors("GREEN", 1);
    public static final Colors BLUE = new Colors("BLUE", 2);

    // Private constructor (only this class can create instances)
    private Colors(String name, int ordinal) {
        super(name, ordinal); // Calls Enum(String name, int ordinal)
    }

    // Values() method (array of all constants)
    public static Colors[] values() {
        return new Colors[]{RED, GREEN, BLUE};
    }

    // valueOf() method (get constant by name)
    public static Colors valueOf(String name) {
        if (name.equals("RED")) return RED;
        if (name.equals("GREEN")) return GREEN;
        if (name.equals("BLUE")) return BLUE;
        throw new IllegalArgumentException("No enum constant " + name);
    }
}
```

---

## 🔎 4. Key Points from Internals

1. Each `enum constant` (`RED`, `GREEN`, `BLUE`) is a **public static final object**.

   * So `Colors.RED` is an object of type `Colors`.
2. `enum` has a **private constructor**, so no one can create new objects.
3. Every enum automatically:

   * extends `java.lang.Enum`
   * gets methods like `values()`, `ordinal()`, `name()`, `valueOf()` for free.
4. The order of constants is stored using an **ordinal (0, 1, 2, …)**.

---

## 🔎 5. Bytecode Check

If you compile an enum and run:

```bash
javap -p Colors.class
```

You’ll see something like:

```
final class Colors extends java.lang.Enum<Colors> {
  public static final Colors RED;
  public static final Colors GREEN;
  public static final Colors BLUE;

  public static Colors[] values();
  public static Colors valueOf(java.lang.String);
  static {};
}
```

This confirms that:

* `RED`, `GREEN`, `BLUE` are objects (`public static final`).
* The compiler inserts a static initializer (`static {}`) block to create these instances.

---

## 🔎 6. Real Example in Code

```java
enum Day {
    MONDAY, TUESDAY;
}

public class EnumInternal {
    public static void main(String[] args) {
        Day d = Day.MONDAY;

        // Name of constant
        System.out.println(d.name()); // MONDAY

        // Ordinal (position)
        System.out.println(d.ordinal()); // 0

        // Values array
        for (Day day : Day.values()) {
            System.out.println(day);
        }
    }
}
```

✅ Output:

```
MONDAY
0
MONDAY
TUESDAY
```

---

## 🎯 Summary

* `enum` in Java = a **class with fixed objects**.
* Each constant = `public static final` instance.
* Constructor = private, called automatically inside a `static` block.
* Enums extend `java.lang.Enum`, giving them methods like `ordinal()`, `name()`, `values()`, `valueOf()`.
* Provides **type safety** compared to `interface constants`.

---

