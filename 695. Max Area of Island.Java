
/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
*/


// method 1: DFS
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[][] pattern = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int[] count = new int[1];
        count[0] = 0;
        int result = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                count[0] = 0;
                dfs(grid, i, j, row, col, count);
                //System.out.println(count[0]);
                result = Math.max(result, count[0]);
            }
        }
        return result;
    }
    
    public void dfs(int[][] grid, int i, int j, int row, int col, int[] count) {
        if(i < 0 || i >= row || j < 0 || j >= col) {
            return;
        }
        if(grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        count[0] += 1;
        for(int k = 0; k < pattern.length; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            dfs(grid, nextRow, nextCol, row, col, count);
        }
    }
}




