
/*
Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
A Palindrome String is one that reads the same backward as well as forward.

Example 1:
Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.

Example 2:
Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:
Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Example 4:
Input: s = "g"
Output: 0

Example 5:
Input: s = "no"
Output: 1
 
Constraints:
1 <= s.length <= 500
All characters of s are lower case English letters.
*/


// method 1: DP(2D)
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    /*
    mbadm
    a -> 0
    ba or ad -> 1
    bad = min(ba, ad) + 1 = 2;
    mbad, badm = 3;
    state: int[][] dp: dp[i][j] indicate the min insertion steps to make substring from i to j a palindrome
    initial state: i == j, dp[i][i] = 0;
    induction rule:
    if s[i] == s[j], dp[i][j] = dp[i+1][j-1]
    else dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1
    result: dp[0][n-1]
    */
    public int minInsertions(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int len = 1; len < n; len++) {
            for(int i = 0; i + len < n; i++) {
                int j = i + len;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][n-1];
    }
}



