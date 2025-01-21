Problem: Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array. 

Solution:

Let's look at two approaches for solving the problem of finding the majority element in an array.

### 1. **Brute Force Solution:**

In the brute force solution, we can simply count the occurrences of each element in the array and check if any element appears more than `n / 2` times.

### Brute Force Solution:
```java
public class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        
        // Iterate through each element
        for (int i = 0; i < n; i++) {
            int count = 0;
            // Count occurrences of nums[i]
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            // If count is greater than n / 2, return the element
            if (count > n / 2) {
                return nums[i];
            }
        }
        
        // Return -1 if no majority element exists (this won't happen as per the problem statement)
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 3};
        System.out.println(solution.majorityElement(nums));  // Output: 3
    }
}
```

### Explanation:
- For each element in the array, we count how many times it appears.
- If an element appears more than `n / 2` times, we return that element.
- This approach has a **time complexity** of **O(n^2)** because for each element, we traverse the entire array to count its occurrences.

---

### 2. **Optimal Solution (Boyer-Moore Voting Algorithm):**

The optimal solution can be achieved using the **Boyer-Moore Voting Algorithm**. The idea behind this algorithm is that the majority element will cancel out the non-majority elements.

### Boyer-Moore Voting Algorithm:
The algorithm works by maintaining a candidate for the majority element and a count of how many times this candidate is "supported" by other elements.

#### Steps:
1. **Phase 1**: Traverse the array to find a potential majority element (the candidate).
2. **Phase 2**: (Optional) Verify that the candidate is indeed the majority element (though this is not necessary for this problem since it's guaranteed).

### Optimal Solution (Boyer-Moore Voting Algorithm):
```java
public class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        
        // Phase 1: Find a potential candidate
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            }
        }
        
        // Phase 2 (Optional): No need to check since the problem guarantees the majority element exists
        // We return candidate because it's guaranteed to be the majority element
        return candidate;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 3};
        System.out.println(solution.majorityElement(nums));  // Output: 3
    }
}
```

### Explanation:
- **Phase 1**: We start by assuming the first element as the candidate and set the `count` to 1.
  - As we go through the array, if the current element is the same as the candidate, we increment the `count`. If it is different, we decrement the `count`.
  - If `count` reaches 0, we switch the candidate to the current element and reset `count` to 1.
- The **time complexity** is **O(n)** because we only traverse the array once.
- The **space complexity** is **O(1)** since we only need a few variables to track the candidate and the count.

### Output:
For the array `nums = {3, 2, 3}`, the output will be `3`.

### Comparison:
- **Brute Force**: Time complexity of O(n^2), which is inefficient for large arrays.
- **Optimal (Boyer-Moore)**: Time complexity of O(n) and space complexity of O(1), which is very efficient.

Since the problem guarantees that a majority element always exists, you don’t need to verify the candidate in the Boyer-Moore approach.
