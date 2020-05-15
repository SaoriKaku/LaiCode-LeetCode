
/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:
Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
*/


// method 1: 2D Array
// time complexity: O(m * n * 4), space complexity: O(1)
class Solution {
    public static final int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int sum = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    int count = 4;
                    for(int k = 0; k < pattern.length; k++) {
                        int x = i + pattern[k][0];
                        int y = j + pattern[k][1];
                        if(x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                            count--;
                        }
                    }
                    sum += count;
                }
            }
        }
        return sum;
    }
}




