Link: https://algomaster.io/practice/dsa-patterns

1. Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements. Note that you must do this in-place without making a copy of the array.
Solution:
To solve this problem in Java, we can use a two-pointer technique. One pointer will iterate through the array and find non-zero elements, and the other pointer will track the position where the next non-zero element should be placed.

Here’s a step-by-step solution:

1. Initialize a pointer `lastNonZeroFoundAt` to keep track of the position where the next non-zero element should be placed.
2. Iterate through the array. Whenever a non-zero element is found, place it at the `lastNonZeroFoundAt` position, and increment `lastNonZeroFoundAt`.
3. After all the non-zero elements are moved to the front, fill the remaining positions of the array with zeros.

Here’s the Java code that implements the solution:

```java
public class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;  // The position where the next non-zero element should go

        // Step 1: Move non-zero elements to the beginning of the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt] = nums[i];
                if (i != lastNonZeroFoundAt) {
                    nums[i] = 0;
                }
                lastNonZeroFoundAt++;
            }
        }
    }
}
```

### Explanation:
- We use `lastNonZeroFoundAt` to track where the next non-zero element should go.
- In the first loop, we check if the current element `nums[i]` is non-zero.
  - If it is, we move it to the position indicated by `lastNonZeroFoundAt` and then increment `lastNonZeroFoundAt`.
  - If `i` is not equal to `lastNonZeroFoundAt`, it means we have moved an element, so we set `nums[i]` to 0.
- By the end of the loop, all non-zero elements are placed at the beginning, and all zero elements are moved to the end.

### Time Complexity:
- **O(n)**: The loop runs once over all the elements in the array, where `n` is the number of elements in the array.

### Space Complexity:
- **O(1)**: The solution is done in-place, so no extra space is used besides the input array.

This method ensures that you don’t need to make a copy of the array, which is the constraint in the problem.
