This is a comprehensive list of Java interview questions covering various topics, including collections, concurrency, exceptions, design patterns, and Spring Boot concepts. Below, I'll provide detailed explanations and examples for each question.

---

### **1. Internal Working of HashMap / HashSet**
#### **HashMap**  
HashMap in Java stores key-value pairs and works using **hashing**. Internally, it uses an **array of linked lists (buckets)** and the **hashCode() method** to determine the bucket index.

- **Steps**:
  1. Compute the hash using `hashCode()`.
  2. Find the bucket using `index = hash & (n-1)`.
  3. If multiple keys have the same index (collision), a linked list or a balanced tree (Java 8+) is used.
  4. On retrieval, the key's `equals()` method is used to locate the correct value.

**Example**:
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("A", 1); // A’s hash decides the bucket
map.put("B", 2); // Different bucket
map.get("A");    // Finds A using hash + equals()
```

#### **HashSet**
HashSet internally uses a **HashMap** where elements are stored as **keys**, and values are dummy constants.

```java
HashSet<String> set = new HashSet<>();
set.add("A"); // Internally stored as map.put("A", PRESENT)
set.add("B");
```

---

### **2. String Mutability & Constant Pool**
- **String is immutable** in Java because:
  - Security (prevents unauthorized modifications).
  - Performance (String pooling in memory).
  - Thread safety.

**Example**:
```java
String s1 = "Hello";  // Stored in String Pool
String s2 = "Hello";  // Reuses the same object
System.out.println(s1 == s2); // true (same reference)
```

Using `new` forces a new object:
```java
String s3 = new String("Hello"); // New object
System.out.println(s1 == s3); // false
```

---

### **3. How to Prepare an Immutable Class**
To make a class immutable:
1. Mark class as `final`.
2. Make all fields `private` and `final`.
3. No setter methods.
4. Use deep copies for mutable fields.

**Example**:
```java
final class ImmutablePerson {
    private final String name;
    
    public ImmutablePerson(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
```

---

### **4. Singleton Class & Ways to Breach Singleton**
A singleton ensures only one instance exists.

**Example (Lazy Initialization)**:
```java
class Singleton {
    private static Singleton instance;
    private Singleton() {}  
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

**Ways to Break Singleton**:
- **Reflection**: Can invoke private constructors.
- **Cloning**: Can create a duplicate instance.
- **Serialization**: Can deserialize to another instance.

**Fix**: Use `Enum Singleton`
```java
enum SingletonEnum {
    INSTANCE;
}
```

---

### **5. Race Condition in Threads**
A **race condition** occurs when multiple threads access shared data simultaneously.

**Example**:
```java
class Counter {
    private int count = 0;
    
    public void increment() {
        count++;
    }
}

Counter c = new Counter();
Thread t1 = new Thread(() -> c.increment());
Thread t2 = new Thread(() -> c.increment());
```

**Fix**: Use `synchronized` or `AtomicInteger`
```java
public synchronized void increment() { count++; }
```
or
```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

---

### **6. Difference Between ClassNotFoundException vs NoClassDefFoundError**
| **Aspect**                 | **ClassNotFoundException** | **NoClassDefFoundError** |
|----------------------------|---------------------------|--------------------------|
| **Cause**                 | Class not found at runtime | Class was compiled but missing at runtime |
| **Checked/Unchecked**     | Checked Exception         | Unchecked Error |
| **Example**               | `Class.forName("XYZ")` and XYZ not found | Class removed from `.jar` |

---

### **7. Writing a Custom Exception**
```java
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
```
Usage:
```java
throw new CustomException("Custom error occurred");
```

---

### **8. Diamond Problem in Java**
The **diamond problem** occurs in multiple inheritance. Java solves it using **interfaces** with default methods.

```java
interface A { default void show() { System.out.println("A"); } }
interface B { default void show() { System.out.println("B"); } }
class C implements A, B {
    public void show() { A.super.show(); } // Resolves conflict
}
```

---

### **9. StringBuilder vs StringBuffer vs String**
| **Aspect**     | **String** (Immutable) | **StringBuffer** (Mutable, Thread-Safe) | **StringBuilder** (Mutable, Faster) |
|---------------|------------------------|---------------------------------|-----------------------------------|
| **Thread Safe** | Yes | Yes (Synchronized) | No |
| **Performance** | Slow | Moderate | Fast |

Example:
```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
```

---

### **10. Comparable vs Comparator (Sorting Employee Objects)**
- **Comparable**: Natural ordering
- **Comparator**: Custom sorting

**Using Comparable**
```java
class Employee implements Comparable<Employee> {
    int age;
    public int compareTo(Employee e) {
        return this.age - e.age;
    }
}
```

**Using Comparator**
```java
Comparator<Employee> ageComparator = (e1, e2) -> e1.age - e2.age;
Collections.sort(list, ageComparator);
```

---

### **11. Can We Keep Prototype Scoped Object in Singleton?**
- Yes, but requires **dependency injection**.
- In Spring, use **Scoped Proxy**.

```java
@Bean
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public Employee employee() { return new Employee(); }
```

---

### **12. Enumeration vs Iterator vs ListIterator**
| **Feature**  | **Enumeration** | **Iterator** | **ListIterator** |
|-------------|---------------|-------------|----------------|
| Modify Collection | No | Yes (remove) | Yes (add/remove) |
| Direction | Forward | Forward | Forward & Backward |

**Example**:
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) { System.out.println(it.next()); }
```

---

I can continue detailing the rest of the questions if you'd like! Let me know which ones you want me to expand further. 🚀
