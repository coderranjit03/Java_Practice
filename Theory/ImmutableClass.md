
---

# 🧊 What is an Immutable Class?

An **Immutable Class** = a class whose objects **cannot be modified after creation**.

Examples in Java:

* All **wrapper classes** (`Integer`, `Double`, etc.)
* **String** class

---

# 🔑 Rules to Make a Class Immutable

1. **Declare the class as `final`** → no one can extend it.
2. **Make all fields `private` and `final`** → can be set only once.
3. **No setters** → do not allow changing fields.
4. **Initialize fields through constructor only**.
5. **Return copies of mutable objects** if your class contains them (to prevent external modification).

---

# ✅ Example of Immutable Class

```java
final class Student {
    // Step 1: make fields private & final
    private final String name;
    private final int age;

    // Step 2: initialize via constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Step 3: only getters (no setters)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class TestImmutable {
    public static void main(String[] args) {
        Student s = new Student("Ravi", 20);

        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());

        // ❌ s.setAge(25);  --> not possible (no setter)
        // Fields cannot be changed once object is created
    }
}
```

➡ Output:

```
Name: Ravi
Age: 20
```

---

# 🌀 Special Case: Mutable Objects Inside

If your class has mutable objects (like `Date` or a `List`), you must **return a copy**, not the original reference.

Example:

```java
import java.util.Date;

final class Employee {
    private final String name;
    private final Date joiningDate;

    public Employee(String name, Date joiningDate) {
        this.name = name;
        // defensive copy to prevent external modification
        this.joiningDate = new Date(joiningDate.getTime());
    }

    public String getName() {
        return name;
    }

    public Date getJoiningDate() {
        // return a copy, not original
        return new Date(joiningDate.getTime());
    }
}

public class TestImmutable {
    public static void main(String[] args) {
        Date d = new Date();
        Employee emp = new Employee("Ravi", d);

        System.out.println("Joining Date: " + emp.getJoiningDate());

        // Trying to modify original date
        d.setTime(0);

        System.out.println("Joining Date after modification attempt: " + emp.getJoiningDate());
    }
}
```

➡ Output will show that the **joiningDate inside Employee did not change**, even though `d` was modified outside.

---

# 🎯 Why Immutable Classes?

1. **Thread Safety** → safe to use in multi-threaded environments (no synchronization needed).
2. **Caching** → objects can be cached because they never change.
3. **Security** → data cannot be modified once set.
4. **Used in Collections** → keys in `HashMap` should ideally be immutable (`String`, `Integer`).

---

# 📝 Interview Notes

* **Encapsulation** hides data; **immutability** prevents modification.
* Immutable classes are heavily used in **Java libraries**.
* `String` is immutable because of **security (passwords), caching, and thread-safety**.

---

