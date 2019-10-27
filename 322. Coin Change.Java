
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

