
/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.

Note:
You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:
Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/


// method 1: 2D DP
// time complexity: O(n^3), space complexity: O(n^2)
class Solution {
    /*
    definition: [left][right]: left, left + 1, ... , right - 1, right, value between [left+1][right-1]
    state: [left][right] 
    induction rule: i in [left + 1, right - 1]
    dp[left][right] = Math.max(left * i * right + dp[left][i] + dp[i][right])
    */
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length + 2;
        int[] input = new int[n];
        for(int i = 0; i < nums.length; i++) {
            input[i + 1] = nums[i];
        }
        input[0] = 1;
        input[n - 1] = 1;
        int[][] dp = new int[n][n];
        for(int dist = 2; dist < n; dist++) {
            for(int left = 0; left + dist < n; left++) {
                int right = left + dist;
                for(int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right], input[left] * input[i] * input[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n - 1];
    }
}





