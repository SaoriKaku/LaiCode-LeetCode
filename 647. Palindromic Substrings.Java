
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 
Note:
The input string length won't exceed 1000.
 
*/


// method 1: DP
// time complexity: O(n^2), space compelxity: O(n^2)
class Solution {
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // i = diff, j = start, i + j = end
        for(int i = 0; i < n; i++) {
            for(int j = 0; i + j < n; j++) {
                if(i == 0) {
                    dp[j][i + j] = true;
                }
                else if(s.charAt(j) == s.charAt(i + j)) {
                    if(i == 1) {
                        dp[j][i + j] = true;
                    }
                    else {
                        dp[j][i + j] = dp[j + 1][i + j - 1];
                    }
                }
                if(dp[j][i + j]) {
                    result++;
                }
            }
        }
        return result;
    }
}





