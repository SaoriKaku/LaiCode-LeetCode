
/*
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.

Note: 1 <= grid.length * grid[0].length <= 20
*/


// method 1: DFS
// time space: O(4^(m * n)), space complexity: (m * n)
class Solution {
    public int[][] pattern = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int uniquePathsIII(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int startRow = 0;
        int startCol = 0;
        int count = 0;
        int[] result = new int[1];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                }
                else if(grid[i][j] == 0) {
                    count++;
                }
            }
        }
        //System.out.println("count = " + count);
        dfs(grid, startRow, startCol, row, col, count, result);
        return result[0];
    }
    public void dfs(int[][] grid, int i, int j, int row, int col, int count, int[] result) {
        if(i < 0 || i >= row || j < 0 || j >= col || count < -1) {
            return;
        }
        if(grid[i][j] == 2 && count == -1) {
            //System.out.println("result: i = " + i + ", j = " + j + ", count = " + count);
            result[0]++;
            return;
        }
        /* 
        obstacle: grid[i][j] == -1
        visited: grid[i][j] == -2
        end: grid[i][j] == 2
        start: grid[i][j] == 1
        */
        if(grid[i][j] == -2 || grid[i][j] == 2 || grid[i][j] == -1) {
            return;
        }
        //System.out.println("i = " + i + ", j = " + j + ", count = " + count);
        grid[i][j] = -2;
        for(int k = 0; k < pattern.length; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            dfs(grid, nextRow, nextCol, row, col, count - 1, result);
        }
        grid[i][j] = 0;
    }
}


