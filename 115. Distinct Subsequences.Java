
/*
Given a string S and a string T, count the number of distinct subsequences of S which equals T.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:
Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

Example 2:
Input: S = "babgbag", T = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
*/


// method 1: DP(2D)
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    /*
          b a b g b a g
        0 1 2 3 4 5 6 7
      0 1 1 1 1 1 1 1 1
    b 1 0 1 1 2 2 3 3 3
    a 2 0 0 1 1 1 1 4 4
    g 3 0 0 0 0 1 1 1 5
    */ 
    public int numDistinct(String s, String t) {
        if(s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return 0;
        }
        int lenT = t.length(), lenS = s.length();
        int[][] dp = new int[lenT + 1][lenS + 1];
        dp[0][0] = 1;
        for(int j = 1; j < lenS + 1; j++) {
            dp[0][j] = dp[0][j-1];
        }
        for(int i = 1; i < lenT + 1; i++) {
            for(int j = 1; j < lenS + 1; j++) {
                if(t.charAt(i-1) == s.charAt(j-1)) {
                    // skip j-1 or not skip j-1
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                }
                else { // has to skip j-1
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[lenT][lenS];
    }
}





