
/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:
Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:
Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.

Example 3:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/


// method 1: DP
// time complexity: O(n), space complexity: O(n)
class Solution {
    /* compute profit
    hold1[i]: max(-price[i], hold1[i-1])
    sold1[i]: max(hold1[i-1] + price[i], sold1[i-1])
    hold2[i]: max(sold1[i-1] - price[i], hold2[i-1])
    sold2[i]: max(hold2[i-1] + price[i], sold2[i-1])
    */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] hold1 = new int[n + 1];
        hold1[0] = Integer.MIN_VALUE;
        int[] sold1 = new int[n + 1];
        int[] hold2 = new int[n + 1];
        hold2[0] = Integer.MIN_VALUE;
        int[] sold2 = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            hold1[i] = Math.max(-1 * prices[i-1], hold1[i-1]);
            sold1[i] = Math.max(hold1[i-1] + prices[i-1], sold1[i-1]);
            hold2[i] = Math.max(sold1[i-1] - prices[i-1], hold2[i-1]);
            sold2[i] = Math.max(hold2[i-1] + prices[i-1], sold2[i-1]);
        }
        // System.out.println(Arrays.toString(sold1));
        // System.out.println(Arrays.toString(sold2));
        return Math.max(sold1[n], sold2[n]);
    }
}





