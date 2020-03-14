
/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:
Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/


// method 1: DP(2D)
// time complexity: O(m * n), space complexity: O(m * n)
/*
    dp[i][j]: whether s[0, j] and p[0, i] are matched
    initial: dp[0][0] = T, 
    String s: dp[0][j] = F, 
    String p: dp[i][0]: 
    if s[i-1] == *, dp[i][0] = dp[i-1][0];
    else dp[i][0] = F
    induction rule: 
    if s[j-1] = p[i-1] || p[i-1] == ?, dp[i][j] = dp[i-1][j-1]
    if s[j-1] = *, dp[i][j] = dp[i-1][j] || dp[i][j-1]
    */
public boolean isMatch(String s, String p) {
    int row = s.length() + 1;
    int col = p.length() + 1;
    boolean[][] dp = new boolean[row][col];
    dp[0][0] = true;
    // set first col
    for(int j = 1; j < col; j++) {
        if(p.charAt(j-1) == '*') {
            dp[0][j] = dp[0][j-1];
        }
    }
    for(int i = 1; i < row; i++) {
        for(int j = 1; j < col; j++) {
            if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                dp[i][j] = dp[i-1][j-1];
            }
            else if(p.charAt(j-1) == '*') {
                dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }
    }
    return dp[row-1][col-1];
}

