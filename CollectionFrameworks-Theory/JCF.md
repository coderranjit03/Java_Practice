
---

# 🔹 Collection Framework in Java

## 1. What is it?

The **Java Collection Framework (JCF)** is a **unified architecture** in Java that provides a set of **interfaces** and **classes** to store, manipulate, and retrieve groups of objects efficiently.

Instead of writing your own data structures (like linked lists, stacks, hash tables), Java gives you **ready-made implementations**.

---

## 2. Why do we need it?

* Arrays in Java are **fixed in size** and lack advanced features.
* The Collection Framework provides **dynamic, flexible, and efficient** data structures.
* It saves development time since we don’t need to reinvent data structures.

---

## 3. Key Interfaces in Collection Framework

All collections are built on top of a few **core interfaces**:

* **Collection** (root interface)

  * **List** → ordered, allows duplicates (ArrayList, LinkedList, Vector, Stack)
  * **Set** → no duplicates (HashSet, LinkedHashSet, TreeSet)
  * **Queue** → follows FIFO or priority (PriorityQueue, ArrayDeque)

* **Map** (not a child of Collection, but part of framework)

  * Key-Value pairs (HashMap, LinkedHashMap, TreeMap, Hashtable)

---

## 4. Hierarchy Diagram (Simplified)
```
                Iterable<T>
                     |
               ┌─────┼─────┐
               |     |     |
             Collection<T> (root for List, Set, Queue)
                     |
    ┌────────────────┼───────────────────┐
    |                |                   |
   List<T>          Set<T>              Queue<T>
    |                |                   |
 ArrayList      HashSet              PriorityQueue
 LinkedList     LinkedHashSet        ArrayDeque
 Vector         TreeSet
 Stack

  (Notice: Map is NOT here ⬇)

               Map<K,V>  (separate branch, not Iterable)
                 |
    ┌────────────┼─────────────┐
    |            |             |
 HashMap    LinkedHashMap    TreeMap
   |
 WeakHashMap
 Hashtable
```

---

## 5. Commonly Used Classes

* **List Implementations**

  * `ArrayList` → dynamic array, fast random access
  * `LinkedList` → doubly linked list, fast insertion/deletion
  * `Vector` / `Stack` → older, synchronized

* **Set Implementations**

  * `HashSet` → stores unique elements, unordered
  * `LinkedHashSet` → maintains insertion order
  * `TreeSet` → sorted order

* **Map Implementations**

  * `HashMap` → key-value storage, fast lookup
  * `LinkedHashMap` → maintains insertion order
  * `TreeMap` → sorted keys

---

## 6. Example Usage

```java
import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        // List example
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Apple"); // duplicates allowed
        System.out.println(list); // [Apple, Banana, Apple]

        // Set example
        Set<String> set = new HashSet<>(list);
        System.out.println(set); // [Apple, Banana] (no duplicates)

        // Map example
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "Python");
        map.put(3, "C++");
        System.out.println(map); // {1=Java, 2=Python, 3=C++}
    }
}
```

---

## 7. Benefits

✔️ Ready-made data structures
✔️ Reduces development effort
✔️ Improves performance
✔️ Standardized way to handle collections


---

### 🔹 Why is `Map` separate?

* `Iterable<T>` → means a collection of elements you can loop through (like `List`, `Set`, `Queue`).
* `Map<K,V>` → stores **pairs (key, value)**, not just single elements → so it does **not fit into Iterable/Collection hierarchy**.

Instead, `Map` gives **collection views** that are iterable:

* `map.keySet()` → `Set<K>`
* `map.values()` → `Collection<V>`
* `map.entrySet()` → `Set<Map.Entry<K,V>>`

---

✅ So the key point:

* `List`, `Set`, `Queue` = Iterable ✅
* `Map` = NOT Iterable ❌ (but provides iterable views).

---

