
/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
*/


// method 1: dp(2D)
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    /* i, j is the length of s and p
    states: dp[i][j]: s[0, i-1] matches p[0, j-1]
    initial: 
    dp[0][0] = true, 
    dp[si][0] = false, 
    dp[0][pi] = dp[0][pi-2] if p[i-1] == *
    general:
    if p[i-1] = '.' or p[i-1] == s[j-1] then dp[si][pi] = dp[si-1][pi-1] 
    if p[i-1] = '*' 
    if s[i-1] == p[i-2] or p[i-1] == '.' then dp[si][pi] = dp[si - 1][pi] || dp[si][pi-2]
    */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int pi = 1; pi <= n; pi++) {
            if(p.charAt(pi-1) == '*') {
                dp[0][pi] = dp[0][pi-2];
            }
        }
        for(int si = 1; si <= m; si++) {
            for(int pi = 1; pi <= n; pi++) {
                if(p.charAt(pi-1) == '.' || p.charAt(pi-1) == s.charAt(si-1)) {
                    dp[si][pi] = dp[si-1][pi-1];
                }
                else if(p.charAt(pi-1) == '*') {
                    if(p.charAt(pi-2) == s.charAt(si-1) || p.charAt(pi-2) == '.') {
                        dp[si][pi] = dp[si-1][pi] || dp[si][pi-2];  
                    }
                    else {
                        dp[si][pi] = dp[si][pi-2];  
                    }
                }
            }
        }
        return dp[m][n];
    }
}

