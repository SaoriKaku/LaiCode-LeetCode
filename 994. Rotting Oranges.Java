
/*
In a given grid, each cell can have one of three values:
the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 
Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/


// method 1: bfs
// time complexity: O(m * n), space complexity: O(m * n)
/*
freshCount: 实际上新鲜橘子个数
freshVisitCount: 能放进queue里面的新鲜橘子
如果不相等，说明不是所有新鲜橘子都能变成烂橘子，return -1
*/
class Solution {
    public int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    freshCount++;
                }
                else if(grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        if(freshCount == 0) return 0;
        int count = -1, freshVisitCount = 0;
        while(!queue.isEmpty()) {
            count++;
            int size = queue.size();
            // System.out.println("size = " + size);
            for(int i = 0; i < size; i++) {
                int[] currPos = queue.poll();
                int x = currPos[0];
                int y = currPos[1];
                for(int j = 0; j < pattern.length; j++) {
                    int nextX = x + pattern[j][0];
                    int nextY = y + pattern[j][1];
                    if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && grid[nextX][nextY] == 1) {
                        freshVisitCount++;
                        grid[nextX][nextY] = 2;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }
        return freshCount == freshVisitCount ? count : -1;
    }
}




