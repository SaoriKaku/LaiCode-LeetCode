
/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:
Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:
Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:
Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 
Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

// method1: BFS
// time complexity: O(n), space complexity: O(n)
class Solution {
    int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        Deque<int[]> queue = new LinkedList<>();
        if(grid[0][0] == 0) {
            queue.offerLast(new int[]{0, 0});
        }
        int layer = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            layer++;
            for(int i = 0; i < size; i++) {
                int[] curr = queue.pollFirst();
                if(curr[0] == grid.length - 1 && curr[1] == grid[0].length - 1) {
                    return layer;
                }
                for(int j = 0; j < pattern.length; j++) {
                    int x = curr[0] + pattern[j][0];
                    int y = curr[1] + pattern[j][1];
                    if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
                        queue.offerLast(new int[] {x, y});
                        grid[x][y] = 2;
                    } 
                }
            }
        }
        return -1;
    }
}
