Here are three common solutions to rotate an integer array to the right by \( k \) steps:

---

### **Solution 1: Reverse the Array (Optimal Approach)**
This approach uses the reverse operation and has \( O(n) \) time complexity and \( O(1) \) space complexity.

Steps:
1. Reverse the entire array.
2. Reverse the first \( k \) elements.
3. Reverse the remaining \( n-k \) elements.

```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length

    reverse(nums, 0, n - 1);       // Reverse the entire array
    reverse(nums, 0, k - 1);       // Reverse the first k elements
    reverse(nums, k, n - 1);       // Reverse the remaining elements
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```

---

### **Solution 2: Using Extra Array**
In this approach, you use a temporary array to place the rotated elements and then copy them back to the original array.

Steps:
1. Create a new array of the same size.
2. Place each element in its rotated position.
3. Copy the new array back to the original array.

```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length
    int[] temp = new int[n];

    for (int i = 0; i < n; i++) {
        temp[(i + k) % n] = nums[i];
    }

    // Copy the rotated elements back into the original array
    System.arraycopy(temp, 0, nums, 0, n);
}
```

---

### **Solution 3: Cyclic Replacements**
This approach moves elements in a cyclic way and ensures each element is placed in its correct position. It has \( O(n) \) time complexity and \( O(1) \) space complexity.

Steps:
1. Iterate through the array and place each element in its new position.
2. Use a counter to ensure all elements are moved exactly once.

```java
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length
    int count = 0; // Tracks how many elements have been placed

    for (int start = 0; count < n; start++) {
        int current = start;
        int prevValue = nums[start];

        do {
            int next = (current + k) % n;
            int temp = nums[next];
            nums[next] = prevValue;
            prevValue = temp;
            current = next;
            count++;
        } while (start != current); // Stop when the cycle ends
    }
}
```

---

### Key Differences:
- **Solution 1 (Reversal):** Efficient and simple, works in-place.
- **Solution 2 (Extra Array):** Straightforward but uses \( O(n) \) extra space.
- **Solution 3 (Cyclic):** In-place and efficient, but a bit tricky to implement correctly.

------------------
Here are three common solutions to rotate an integer array to the **left** by \( k \) steps:

---

### **Solution 1: Reverse the Array (Optimal Approach)**  
This approach uses the reverse operation, similar to rotating to the right, but with different steps. It has \( O(n) \) time complexity and \( O(1) \) space complexity.

**Steps**:  
1. Reverse the first \( k \) elements.  
2. Reverse the remaining \( n-k \) elements.  
3. Reverse the entire array.  

**Code**:  
```java
public void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length

    reverse(nums, 0, k - 1);       // Reverse the first k elements
    reverse(nums, k, n - 1);       // Reverse the remaining elements
    reverse(nums, 0, n - 1);       // Reverse the entire array
}

private void reverse(int[] nums, int start, int end) {
    while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```

---

### **Solution 2: Using Extra Array**  
This approach uses a temporary array to rearrange the elements for the left rotation. It is straightforward but uses \( O(n) \) extra space.  

**Steps**:  
1. Create a temporary array.  
2. Place elements from the \( k^{th} \) position in the beginning of the new array.  
3. Copy the temporary array back into the original array.  

**Code**:  
```java
public void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length
    int[] temp = new int[n];

    for (int i = 0; i < n; i++) {
        temp[i] = nums[(i + k) % n];
    }

    // Copy the rotated elements back into the original array
    System.arraycopy(temp, 0, nums, 0, n);
}
```

---

### **Solution 3: Cyclic Replacements**  
This approach rotates the array in-place by using cyclic replacements. It has \( O(n) \) time complexity and \( O(1) \) space complexity.  

**Steps**:  
1. Move each element to its correct position in a cyclic manner.  
2. Track how many elements have been moved to avoid overwriting or skipping.  

**Code**:  
```java
public void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    k = k % n; // Handle cases where k is greater than the array length
    int count = 0; // Track how many elements have been rotated

    for (int start = 0; count < n; start++) {
        int current = start;
        int prevValue = nums[start];

        do {
            int next = (current - k + n) % n; // Calculate the new position (handle negative indices)
            int temp = nums[next];
            nums[next] = prevValue;
            prevValue = temp;
            current = next;
            count++;
        } while (start != current); // Stop when the cycle ends
    }
}
```

---

### **Key Differences**:
- **Solution 1 (Reversal)**: Efficient and works in-place, easy to implement.  
- **Solution 2 (Extra Array)**: Simple to understand but uses extra space.  
- **Solution 3 (Cyclic)**: In-place and efficient, but implementation is a bit tricky.  

Let me know if you'd like further clarification or a comparison of these approaches! 😊
