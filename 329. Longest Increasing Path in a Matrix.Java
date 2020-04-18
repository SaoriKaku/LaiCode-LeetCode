
/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/


// method 1: dfs + memorization
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    int[][] pattern = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int[][] cache = new int[row][col];
        int max = 1;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(cache[i][j] == 0) {
                    dfs(matrix, i, j, row, col, cache);
                    max = Math.max(max, cache[i][j]);
                }
            }
        }
        //print2DArray(cache, row, col);
        return max;
    }
    
    public void dfs(int[][] matrix, int x, int y, int row, int col, int[][] cache) {
        cache[x][y] = 1;
        for(int i = 0; i < pattern.length; i++) {
            int nextX = x + pattern[i][0];
            int nextY = y + pattern[i][1];
            int currCount = 1;
            if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && matrix[x][y] < matrix[nextX][nextY]) {
                if(cache[nextX][nextY] > 0) {
                    currCount += cache[nextX][nextY];
                }
                else {
                    dfs(matrix, nextX, nextY, row, col, cache);
                    currCount = cache[nextX][nextY] + 1;
                }
            }
            cache[x][y] = Math.max(cache[x][y], currCount);
        }
    }
    
    public void print2DArray(int[][] cache, int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(cache[i][j] + " ");
            }
            System.out.println();
        }
    }
}

