List of Selected java interview questions.
1 Internal working of hashmap / Hashset. 
2 String mutability & constat pool.
3 How to prepare Immutable class
4 How to achieve singleton class (ways to breach singlitter behaviour)
5 Race condition in thread.
6 Difference between ClassNotFound Exception Vs NoClassDefinition Error 
7 How to write custom Exception in java. 
8 Diamond Ring problem Solved by java 
9 StringBuilder Ve StringBuffer vs String. 
10 Comparable & comparator in java (sort the employee abject in collection)
11 Can we keep prototype scoped abject in Singleton
12 Enumeration, Iterator, List Iteration
13 Define Arraylist not allowing duplicates (override the add method)
--------------
14 Why Set does not allow duplicates?
	-> set uses Map as storage internally
	-> Map does not allow duplicates
15 Equals & Hashcode contract
16 Explain Collectors Interface & Arrays Interface
17 Executor Service in threads
18 Difference between fail fast & fail safe iterator
	-> Fail-Fast : ArrayList, HashMap, Vector
	-> Fail-Safe: CopyOnWriteArrayList, CopyOnWriteSet, ConcurrentHashMap
19 Whats is transient, volatile, strictfp?
20 Working of Concurrent Hashmap
21 Hashmap vs Concurrent Hashmap (Segment locking, Bucket Locking)
22 How will you fetch item added against "null" key in hashmap?
23 Can we add key OR value Null in Concuttim????
24 HashTable vs ConcurrentHashMap?
--------------
22 JVM architecture
23 How to create custom exception?
24 Difference between concurrent collection and synchronized collection?
25 Hashmap vs HashTable
26 When to use concurrent Hashmap in java? --when multiple readers available.
27 How to make List, Map & Set READ_ONLY?
	List<String> list = new ArrayList<>();
	list = Collections.unModifiableList(list);
	set = Collections.unModifiableSet(set);
	map = Collections.unModifiableMap(map);
28 Sort arraylist using custom rule/ascending/descending
	-> comparator implementation
	-> Collection.sort()
29 Sort ArrayList in a list by case insensitive order.
	-> list = Arrays.asList("AA","Aa","aA","aa");
	-> Collection.sort(list, String.CASE_INSENSITIVE_ORDER);
30 Convert Map to List in java
31 Compare String or Date class in java?
	->  string & date classes already implements Comparable
	-> d1.compareTo(d2) & s1.compareTo(s2) will work
--------------
32 Explain factor Design Pattern
33 Read database till joins
34 Shallow cloning and deep cloning
35 Reserved literals?
	-> null, true, false are not keywords
36 Implement Singleton Design Pattern & use cases
	-> provide private constructor
	-> lazy loading & identify check for creation of object
	-> get method provided	
	-> all members static
	-> use case 1) Clonable 2)Serialization 3) MultiThreading 4) Reflection
37 If @Autowired mentioned at the top of object declaration directly, will it be setter injectiong or constructor injection
	-> It uses reflection API & executes setter injection
	-> Setter injection = Option dependency
	-> Constructor Injection = Mandatory dependency
38 Difference between @PathParameter, @PathVariable, @RequestParameter
39 What is stream?
40 THread execution hierarchy for a web request.
41 What are log levels?
	-> Trace->Debug->Info->Warn->Error->Fatal
	-> When we set the level-log, including that level, next all levels will be printed.
	-> Default in springboot  = INFO
--------------
42 What is Authorfication and Authorization ?
43 How mutithreading model provides service to first request on server?
44 How can your ensure that multiple parallel co-working threads will end at same time?
45 How many ways are there to iterate over map, set, list?
46 Difference between Put & Post? What os indeporat request?
47 Working of @Trasactional
48 Copy data from one db and save it to another db
49 Add employee object in set with names sorted.
50 Save employee object in db using spring & hibernate
----------------------------------------
This is a comprehensive list of Java interview questions covering various topics, including collections, concurrency, exceptions, design patterns, and Spring Boot concepts. Below, I'll provide detailed explanations and examples for each question.

### Java Interview Questions with Detailed Explanations

#### **1. Internal Working of HashMap / HashSet**
##### **HashMap:**
- Uses **hashing** to store key-value pairs.
- Uses **array of buckets** where each bucket can have multiple entries (linked list or tree structure from Java 8+).
- Collision is handled using chaining (Linked List or Tree after threshold).

**Example:**
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.get("A");
```

##### **HashSet:**
- Internally uses a `HashMap` with a constant dummy value.
```java
HashSet<String> set = new HashSet<>();
set.add("A");
```

---
#### **2. String Mutability & Constant Pool**
- Strings are **immutable**.
- String literals are stored in the **String Pool** for memory optimization.

**Example:**
```java
String s1 = "Hello";
String s2 = "Hello";
System.out.println(s1 == s2); // true
```

---
#### **3. How to Prepare an Immutable Class**
- Declare class as `final`.
- Make fields `private` and `final`.
- Provide only getters, no setters.

**Example:**
```java
final class ImmutablePerson {
    private final String name;
    public ImmutablePerson(String name) { this.name = name; }
    public String getName() { return name; }
}
```

---
#### **4. How to Achieve Singleton Class (Ways to Breach Singleton Behavior)**
- **Lazy Initialization** Singleton:
```java
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```
- **Breaking Singleton:** Reflection, Cloning, Serialization.
- **Fix:** Enum Singleton:
```java
enum SingletonEnum {
    INSTANCE;
}
```

---
#### **5. Race Condition in Threads**
Occurs when multiple threads access shared data concurrently.

**Fix:** Use `synchronized` or `AtomicInteger`:
```java
public synchronized void increment() { count++; }
```

---
#### **6. Difference Between ClassNotFoundException vs NoClassDefFoundError**
| **Aspect** | **ClassNotFoundException** | **NoClassDefFoundError** |
|---|---|---|
| **Cause** | Class not found at runtime | Class compiled but missing at runtime |
| **Type** | Checked Exception | Runtime Error |

---
#### **7. Writing a Custom Exception**
```java
class CustomException extends Exception {
    public CustomException(String message) { super(message); }
}
throw new CustomException("Custom error occurred");
```

---
#### **8. Diamond Problem in Java**
Java solves this issue using **interfaces with default methods**.
```java
interface A { default void show() { System.out.println("A"); } }
interface B { default void show() { System.out.println("B"); } }
class C implements A, B {
    public void show() { A.super.show(); }
}
```

---
#### **9. StringBuilder vs StringBuffer vs String**
| **Aspect** | **String** (Immutable) | **StringBuffer** (Thread-Safe) | **StringBuilder** (Fast) |
|---|---|---|---|
| **Thread Safe** | Yes | Yes | No |
| **Performance** | Slow | Moderate | Fast |

Example:
```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
```

---
#### **10. Comparable vs Comparator (Sorting Employee Objects)**
- **Comparable** (Natural Order):
```java
class Employee implements Comparable<Employee> {
    int age;
    public int compareTo(Employee e) { return this.age - e.age; }
}
```
- **Comparator** (Custom Sorting):
```java
Comparator<Employee> ageComparator = (e1, e2) -> e1.age - e2.age;
Collections.sort(list, ageComparator);
```

---
#### **11. Can We Keep Prototype Scoped Object in Singleton?**
- Singleton beans have a single instance per application context.
- Prototype beans have new instances for each request.
- **Yes**, but we need to manually inject prototype beans in a singleton.

**Example:**
```java
@Component
class SingletonBean {
    @Autowired
    private ObjectProvider<PrototypeBean> prototypeBeanProvider;
    public PrototypeBean getPrototypeBean() {
        return prototypeBeanProvider.getObject();
    }
}
```

---
#### **12. Enumeration, Iterator, ListIterator**
- `Enumeration` (Legacy, Read-Only):
```java
Enumeration<Integer> e = Collections.enumeration(list);
while (e.hasMoreElements()) System.out.println(e.nextElement());
```
- `Iterator` (Fail-Fast):
```java
Iterator<Integer> it = list.iterator();
while (it.hasNext()) System.out.println(it.next());
```
- `ListIterator` (Bidirectional):
```java
ListIterator<Integer> listIt = list.listIterator();
while (listIt.hasNext()) System.out.println(listIt.next());
```

---
#### **13. Define ArrayList Not Allowing Duplicates**
To enforce uniqueness, override `add()` method.
```java
class UniqueArrayList<E> extends ArrayList<E> {
    @Override
    public boolean add(E e) {
        if (this.contains(e)) return false;
        return super.add(e);
    }
}
```
Usage:
```java
List<Integer> list = new UniqueArrayList<>();
list.add(1);
list.add(1); // Will not be added
```

---
This document provides explanations and examples for selected Java interview questions. Let me know if you need modifications or additional topics!

