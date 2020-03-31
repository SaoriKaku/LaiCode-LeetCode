
/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
*/


// method 1: DP(2D)
// time complexity: O(m * n), space complexity: (m * n)
/*
    state: s3 = s1[0, i] interleave s2[0, j] = s3[0, i+j]
    intial: if(s1[])
             a  a  b  c  c
          0  1  2  3  4  5
       0  F  T  T  F  F  F
    d  1  F  F  T  T  F  F 
    b  2  F  F  T
    b  3  F
    c  4  F
    a  5  F
    if(s1[i-1] == s3[i+j-1]) dp[i][j] = dp[i-1][j]
    if(s2[j-1] == s3[i+j-1]) dp[i][j] = dp[i][j-1]
    */
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if(len3 != len1 + len2) {
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for(int i = 1; i < len1 + 1; i++) {
            if(s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0]) {
                dp[i][0] = true;
            }
        }
        for(int j = 1; j < len2 + 1; j++) {
            if(s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]) {
                dp[0][j] = true;
            }
        }
        for(int i = 1; i < len1 + 1; i++) {
            for(int j = 1; j < len2 + 1; j++) {
                if(s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]) {
                    dp[i][j] = true;
                }
                if(s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[len1][len2];
    }
}



