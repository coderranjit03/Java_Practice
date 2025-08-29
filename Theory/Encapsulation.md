
---

# 🔒 What is Encapsulation?

**Encapsulation** = *wrapping data (fields) and methods (functions) together in a single unit (class)* and controlling access to that data.

In simple words:
👉 You **hide the internal details (data)** of a class and only expose controlled access through **methods (getters/setters)**.
👉 It’s like a capsule: medicine (data) is hidden inside, and you control how it’s taken.

---

# 🔑 Key Concepts

1. **Fields are private** → cannot be directly accessed from outside the class.
2. **Public methods (getters/setters)** → used to *read* or *modify* the private data safely.
3. Encapsulation enforces **data hiding** + **control**.
4. It also helps in **maintainability** (you can change implementation without affecting external code).

---

# ✅ Example Without Encapsulation (❌ Bad Practice)

```java
class Student {
    public String name;
    public int age;
}

public class Test {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "Ravi";   // Direct access (anyone can modify it)
        s.age = -5;        // Invalid value (no validation)
        System.out.println(s.name + " " + s.age);
    }
}
```

➡ Problem: no control → someone can set negative age, which is illogical.

---

# ✅ Example With Encapsulation (✔ Good Practice)

```java
class Student {
    // Step 1: make fields private
    private String name;
    private int age;

    // Step 2: provide public getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Optional: add validation
        if(name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name!");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Add validation
        if(age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive!");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("Ravi");
        s.setAge(20);

        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
    }
}
```

➡ Output:

```
Name: Ravi
Age: 20
```

---

# 🎯 Why Use Encapsulation?

1. **Data Hiding** → outside world cannot directly change fields.
2. **Validation/Control** → prevent invalid data (like negative age).
3. **Flexibility** → you can change internal logic without breaking external code.
4. **Read-only or Write-only** → by exposing only `get` or only `set`.

---

# 🔍 Real-world Example

Think of a **bank account**:

* Balance is private (no one should change it directly).
* You deposit/withdraw using methods (with rules).

```java
class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdraw attempt!");
        }
    }
}
```

---

# 📝 Interview Quick Points

* Encapsulation = wrapping + hiding + controlling access.
* Achieved using **private fields + getters/setters**.
* Ensures **security + maintainability**.
* **Encapsulation ≠ Abstraction**:

  * Encapsulation hides data (implementation detail).
  * Abstraction hides implementation complexity (showing only essential features).

---

