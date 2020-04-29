
/*
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

Example 1:
Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 

Example 2:
Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Example 3:
Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3
 
Note:
1 <= R, C <= 100
0 <= A[i][j] <= 10^9
*/


// method 1: Heap
// time complexity: m * n * log(m * n), space complexity: O(m * n)
class Solution {
    public int[][] pattern = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maximumMinimumPath(int[][] A) {
        if(A == null || A.length == 0) {
            return 0;
        }
        int row = A.length, col = A[0].length;
        int maxMin = A[0][0];
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (A[b[0]][b[1]] - A[a[0]][a[1]]));
        maxHeap.offer(new int[]{0, 0});
        while(!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int x = curr[0];
            int y = curr[1];
            maxMin = Math.min(maxMin, A[x][y]);
            if(x == row - 1 && y == col - 1) {
                return maxMin;
            }
            for(int i = 0; i < 4; i++) {
                int nextX = x + pattern[i][0];
                int nextY = y + pattern[i][1];
                if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !visited[nextX][nextY]) {          
                    visited[nextX][nextY] = true;
                    maxHeap.offer(new int[]{nextX, nextY});
                }
            }
        }
        return A[0][0];
    }
}





