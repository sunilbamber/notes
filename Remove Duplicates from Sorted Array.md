Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
Problem: Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.

Solution:

Let's solve the problem of removing duplicates from a sorted integer array and returning the number of unique elements. 

### Problem Breakdown:
1. We are given a sorted array in non-decreasing order, and we need to remove duplicates in-place.
2. The order of the unique elements should remain unchanged.
3. After removing duplicates, the array should have only unique elements in the first `k` positions, and the remaining elements do not matter.
4. Return `k`, which is the count of unique elements.

### Two Approaches:

### 1. **Brute Force Solution:**
In the brute force approach, we can use two loops:
- One loop to check each element and another to remove any duplicates by shifting the unique elements forward.

### Brute Force Solution:

```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int k = 0;  // Position for the next unique element
        
        // Loop through the array
        for (int i = 1; i < n; i++) {
            boolean duplicateFound = false;
            // Check for duplicates by checking all elements before
            for (int j = 0; j < k; j++) {
                if (nums[i] == nums[j]) {
                    duplicateFound = true;
                    break;
                }
            }
            if (!duplicateFound) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 2, 2, 3, 4, 4};
        System.out.println(solution.removeDuplicates(nums));  // Output: 4
    }
}
```

### Explanation:
- We start with an empty result array and iterate through `nums`.
- For each element, we check if it has already appeared before in the result portion of the array.
- If it has not appeared before, we move it to the next position in the result portion of the array and increment the position (`k`).
- **Time Complexity**: O(n^2) due to the inner loop that checks for duplicates.
- **Space Complexity**: O(1), as the solution is done in-place without using additional space.

---

### 2. **Optimal Solution (Two Pointer Approach):**

The optimal solution involves using two pointers:
- One pointer (`i`) will iterate through the array to check for duplicates.
- Another pointer (`k`) will track the position where the next unique element should be placed.

Since the array is already sorted, we can simply compare each element with the previous one. If they are different, we place the current element in the `k`-th position.

### Optimal Solution:

```java
public class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int k = 1;  // Start from the second element
        
        // Iterate over the array
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 2, 2, 3, 4, 4};
        System.out.println(solution.removeDuplicates(nums));  // Output: 4
    }
}
```

### Explanation:
- We initialize `k` to 1 because the first element is always unique by itself.
- Starting from the second element (`i = 1`), we compare it with the previous element (`nums[i] != nums[i-1]`).
- If the current element is different from the previous one, it is a unique element, and we place it at the `k`-th position.
- After processing all elements, the array will have unique elements in the first `k` positions, and the value of `k` will represent the number of unique elements.
- **Time Complexity**: O(n), where `n` is the length of the array, because we iterate over the array once.
- **Space Complexity**: O(1), since the solution works in-place.

### Example Walkthrough:

For the array `{1, 1, 2, 2, 3, 4, 4}`:

- After the first iteration, `k = 1`, `nums = [1, 1, 2, 2, 3, 4, 4]`.
- After the second iteration, `k = 2`, `nums = [1, 2, 2, 2, 3, 4, 4]`.
- After the third iteration, `k = 3`, `nums = [1, 2, 3, 2, 3, 4, 4]`.
- After the fourth iteration, `k = 4`, `nums = [1, 2, 3, 4, 3, 4, 4]`.

Thus, the first `4` elements are the unique elements: `{1, 2, 3, 4}`, and the function will return `4`.

### Comparison of Approaches:

- **Brute Force**: Time complexity of **O(n^2)** and space complexity of **O(1)**.
- **Optimal (Two Pointers)**: Time complexity of **O(n)** and space complexity of **O(1)**.

For large arrays, the optimal solution is significantly more efficient.
