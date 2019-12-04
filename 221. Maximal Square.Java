
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/


// method 1: DP
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxLength = 0;
        for(int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] == '0' ? 0 : 1;
            maxLength = Math.max(maxLength, dp[i][0]);
        }
        for(int i = 0; i < col; i++) {
            dp[0][i] = matrix[0][i] == '0' ? 0 : 1;
            maxLength = Math.max(maxLength, dp[0][i]);
        }
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(matrix[i][j] == '0') {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength * maxLength;
    }
}




