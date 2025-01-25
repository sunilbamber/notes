Sure! Here's the brute force and optimal solutions for the problem in Java.

### Brute Force Solution:
The brute force approach would involve checking every possible pair of days: one to buy and the other to sell. This results in a time complexity of O(n^2), where `n` is the number of days.

#### Brute Force Code:

```java
public class StockProfit {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        
        // Brute force: Check every pair of days to buy and sell
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                // If selling on day j gives more profit than previously recorded, update maxProfit
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum Profit (Brute Force): " + maxProfit(prices)); // Output: 5
    }
}
```

### Explanation of Brute Force:
- The outer loop iterates over every possible day to buy the stock (`i`), and the inner loop iterates over every subsequent day (`j`) to sell the stock.
- For each pair of days, it calculates the profit and keeps track of the maximum profit.
- If no profit can be made, the function will return `0`.

### Optimal Solution:
The optimal solution uses a single pass through the array to find the minimum price up to that point and calculates the potential profit at each step. This reduces the time complexity to O(n), where `n` is the number of days.

#### Optimal Code:

```java
public class StockProfit {
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        // Iterate through the prices to find the maximum profit
        for (int i = 0; i < prices.length; i++) {
            // Update the minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);
            // Calculate the potential profit if selling at the current price
            int profit = prices[i] - minPrice;
            // Update the maximum profit if needed
            maxProfit = Math.max(maxProfit, profit);
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum Profit (Optimal): " + maxProfit(prices)); // Output: 5
    }
}
```

### Explanation of Optimal Solution:
- We keep track of the minimum price seen so far (`minPrice`).
- For each day, we calculate the profit if we were to sell the stock on that day (i.e., `prices[i] - minPrice`).
- We update the maximum profit as we iterate through the days.

### Time Complexity:
- **Brute Force Solution:** O(n^2) because of the nested loops.
- **Optimal Solution:** O(n) because we only iterate over the array once.

This optimal solution is much more efficient and suitable for larger datasets!
