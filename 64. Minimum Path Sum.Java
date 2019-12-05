
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/


// method 1: dfs, but Time Limit Exceeded
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[] minSum = new int[1];
        minSum[0] = Integer.MAX_VALUE;
        dfs(grid, 0, 0, row, col, 0, minSum);
        return minSum[0] + grid[row - 1][col - 1];
    }
    public void dfs(int[][] grid, int i, int j, int row, int col, int sum, int[] minSum) {
        if(i == row - 1 && j == col - 1) {
            minSum[0] = Math.min(minSum[0], sum);
            return;
        }
        if(i == row || j == col) {
            return;
        }
        dfs(grid, i + 1, j, row, col, sum + grid[i][j], minSum);
        dfs(grid, i, j + 1, row, col, sum + grid[i][j], minSum);
    }
}


// method 2: DP
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int[][] sum = new int[row][col];
        // initialize row[0]
        sum[0][0] = grid[0][0];
        for(int i = 1; i < col; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }
        // initialize col[0]
        for(int i = 1; i < row; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[row - 1][col - 1];
    }
}
