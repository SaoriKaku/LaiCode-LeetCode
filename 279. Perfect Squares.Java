
/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/


// method 1: DP(1D)
// time complexity: O(n * sqrt(n)), space complexity: O(n)
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for(int i = 1; i * i <= n; i++) {
            dp[i*i] = 1;
        }
        for(int i = 1; i < n+1; i++) {
            if(dp[i] == 1) continue;
            for(int j = 1; j * j < i; j++) {
                // dp[4] = 1, dp[5] = dp[4] + 1 = 2
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}


