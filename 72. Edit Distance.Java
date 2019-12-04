
/*
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/


// method 1: DP
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) {
            return -1;
        }
        if(word1.length() == 0) {
            return word2.length();
        }
        if(word2.length() == 0) {
            return word1.length();
        }
        int row = word1.length() + 1;
        int col = word2.length() + 1;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++) {
            dp[i][0] = i;
        }
        for(int i = 0; i < col; i++) {
            dp[0][i] = i;
        }
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                    continue;
                }
                // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]) + 1;
            }
        }
        return dp[row-1][col-1];
    }
}



