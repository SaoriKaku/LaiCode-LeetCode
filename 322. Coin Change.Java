
/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/


// method 1: DFS + Greedy(prune)
// coins.length = m, amount = n, time complexity: O(m^n), space complexity: O(m)
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0) {
            return -1;
        }
        if(amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        int[] sums = new int[coins.length];
        coinChangeHelper(coins, amount, min, 0, coins.length - 1);
        return min[0] == Integer.MAX_VALUE ? -1 : min[0];
    }
    public void coinChangeHelper(int[] coin, int amount, int[] min, int sum, int layer) {
        if(layer == -1) {
            if(amount == 0) {
                min[0] = sum;
            }
            return;
        }
        for(int i = amount / coin[layer]; i >= 0; i--) {
            if(sum + i > min[0]) {
               return; 
            }
            coinChangeHelper(coin, amount - i * coin[layer], min, sum + i, layer - 1);
        }
    }
}


// method 2: DP(2D)
// coins.length = m, amount = n, time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[][] dp = new int[coins.length][amount + 1];
        // whether coins[i] could be used
        for(int i = 0; i < coins.length; i++) {
            // j = amount, from 0 to amount
            for(int j = 0; j < amount + 1; j++) {
                // col one
                if(j == 0) {
                    dp[i][0] = 0;
                }
                // row one
                else if(i == 0) {
                    dp[0][j] = j % coins[0] == 0 ? j / coins[0] : Integer.MAX_VALUE;
                }
                else if(j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                    // if(dp[i][j] == Integer.MIN_VALUE) {
                    //     System.out.println("test1 i = " + i + ", j = " + j);
                    // }
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // for(int i = 0; i < coins.length; i++) {
        //     for(int j = 0; j < amount + 1; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }
}


// method 3: DP(2D), optimized method 2
// coins.length = m, amount = n, time complexity: O(m * n), space complexity: O(n)
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int j = 0; j < amount + 1; j++) {
            dp[j] = j % coins[0] == 0 ? j / coins[0] : Integer.MAX_VALUE;
        }
        // whether coins[i] could be used
        for(int i = 1; i < coins.length; i++) {
            // j = amount, from 0 to amount
            for(int j = 1; j < amount + 1; j++) {
                if(j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}




