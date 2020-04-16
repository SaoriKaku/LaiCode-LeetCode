
/*
You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 
Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/


// method 1: bfs
// time complexity: O(m * n), space complexity: O(1)
class Solution {
    private int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) {
            return;
        }
        int row = rooms.length, col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while(!queue.isEmpty()) {
            int[] currNode = queue.poll();
            int x = currNode[0];
            int y = currNode[1];
            for(int i = 0; i < 4; i++) {
                int nextX = x + pattern[i][0];
                int nextY = y + pattern[i][1];
                if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && rooms[nextX][nextY] == Integer.MAX_VALUE) {
                    rooms[nextX][nextY] = rooms[x][y] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}


