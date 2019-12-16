
/*
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:

Input: amount = 10, coins = [10] 
Output: 1

Note:

You can assume that
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
*/


// method 1: DP(2D)
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0) {
            return 1;
        }
        if(coins == null || coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[][] dp = new int[coins.length][amount + 1];
        // we could used the coins from coins[0] to coins[i](including)
        for(int i = 0; i < coins.length; i++) {
            // j = amount, from 0 to amount
            for(int j = 0; j < amount + 1; j++) {
                if(j == 0) {
                    dp[i][0] = 1;
                }
                else if(i == 0) {
                    dp[0][j] = j % coins[0] == 0 ? 1 : 0;
                }
                else if(j >= coins[i]) {
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        // for(int i = 0; i < coins.length; i++) {
        //     // j = amount, from 0 to amount
        //     for(int j = 0; j < amount + 1; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dp[coins.length - 1][amount];
    }
}


// method 2: DP(2D), optimized method 1
// time complexity: O(m * n), space complexity: O(m)
class Solution {
    public int change(int amount, int[] coins) {
        if(amount == 0) {
            return 1;
        }
        if(coins == null || coins.length == 0) {
            return 0;
        }
        //Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int j = 1; j < amount + 1; j++) {
            dp[j] = j % coins[0] == 0 ? 1 : 0;
        }
        // we could used the coins from coins[0] to coins[i](including)
        for(int i = 1; i < coins.length; i++) {
            // j = amount, from 0 to amount
            for(int j = 1; j < amount + 1; j++) {
                if(j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }
}




