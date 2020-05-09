
/*
In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
Return the maximum amount of gold you can collect under the conditions:
Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 
Example 1:
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.

Example 2:
Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 
Constraints:
1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
*/


// method 1: dfs
// time complexity: O((m * n)^2), space complexity: O(m * n)
class Solution {
    public int getMaximumGold(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int result = 0;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] != 0) {
                    int[] maxSum = new int[1];
                    dfs(grid, i, j, row, col, 0, maxSum, visited);
                    result = Math.max(result, maxSum[0]);
                }
            }
        }
        return result;
    }
    public static final int[][] pattern = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void dfs(int[][] grid, int x, int y, int row, int col, int sum, int[] maxSum, boolean[][] visited) {
        if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || grid[x][y] == 0) {
            return;
        }
        sum += grid[x][y];
        maxSum[0] = Math.max(sum, maxSum[0]);
        visited[x][y] = true;
        for(int i = 0; i < pattern.length; i++) {
            int nextX = x + pattern[i][0];
            int nextY = y + pattern[i][1];
            dfs(grid, nextX, nextY, row, col, sum, maxSum, visited);
        }
        visited[x][y] = false;
        sum -= grid[x][y];
    }
}




