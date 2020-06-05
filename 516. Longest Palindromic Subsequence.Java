
/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:
"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:
"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
*/


// method 1: DP(2D)
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    /*
    state: int[i][j], the max length of a palindrome from i to j
    initial: i == j, dp[i][i] = 1;
    induction rule: 
    if(charAt(i) == charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
    dp[i][j] = max(dp[i+1][j], dp[i][j-1])
    */
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int len = 0; len < n; len++) {
            for(int i = 0; i + len < n; i++) {
                int j = i + len;
                if(len == 0) {
                    dp[i][j] = 1;
                }
                else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }
                    else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}




