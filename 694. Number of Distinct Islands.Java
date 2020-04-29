
/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.

Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
*/


// method 1: dfs + HashSet(record direction + go back)
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int[][] pattern = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numDistinctIslands(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        Set<String> pathIndexSet = new HashSet<>();
        int row = grid.length, col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    // 4: no direction, start point
                    dfs(grid, i, j, row, col, 4, sb); 
                    pathIndexSet.add(String.valueOf(sb));
                }
            }
        }
        return pathIndexSet.size();
    }
    public void dfs(int[][] grid, int x, int y, int row, int col, int index, StringBuilder path) {
        if(x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) return;
        grid[x][y] = 0;
        path.append(index);
        for(int i = 0; i < pattern.length; i++) {
            int nextX = x + pattern[i][0];
            int nextY = y + pattern[i][1];
            dfs(grid, nextX, nextY, row, col, i, path);
            // 5: back to origin
            path.append(5);
        }
    }
}



