
/*
Given two strings text1 and text2, return the length of their longest common subsequence.
A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
If there is no common subsequence, return 0.

Example 1:
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 
Constraints:
1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
*/


// method 1: DP(2D)
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    /*
        a b c d e
    a   1 0 0 0 0
    c   0 1 2 2 2
    e   0 1 2 2 3
    state: int[][] dp: dp[i][j], the longest common subsequence for 
    text1(from index 0 to i-1) and text2(from index 0 to j-1)
    initial: dp[i][0] = 0, dp[0][j] = 0
    induction rule: 
    if text1.charAt(i-1) == text2.charAt(j-1) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
    else dp[i][j] = dp[i-1][j-1] + 1;
    */
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else {
                    if(text1.charAt(i-1) == text2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[m][n];
    }
}


