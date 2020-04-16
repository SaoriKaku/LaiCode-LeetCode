
/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
*/


// method 1: bfs
// time complexity: O(k * m * n), space complexity: O(m * n)
class Solution {
    private int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minTotalDistance(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int[][] dist = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    int layer = 0;
                    boolean[][] visited = new boolean[row][col];
                    visited[i][j] = true;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        layer++;
                        for(int m = 0; m < size; m++) {
                            int[] currPos = queue.poll();
                            int x = currPos[0];
                            int y = currPos[1];
                            for(int n = 0; n < pattern.length; n++) {
                                int nextX = x + pattern[n][0];
                                int nextY = y + pattern[n][1];
                                if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && !visited[nextX][nextY]) {
                                    dist[nextX][nextY] += layer;
                                    visited[nextX][nextY] = true;
                                    queue.offer(new int[]{nextX, nextY});
                                }
                            }
                        }
                    } // end while
                } // end if
            }
        }
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                minDist = Math.min(minDist, dist[i][j]);
            }
        }
        return minDist;
    }
}


// method 2: median = meeting point, k = the number of position where grid[i][j] == 1
// time complexity: O(m * n + klogk), space complexity: O(k)
class Solution {
    /*
    x: 0 2 4, 0+2+4=6, 6/3=2
    y: 0 0 2, 0+0+2=2, 2/3=0
    */
    public int minTotalDistance(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        List<Integer> listI = new ArrayList<>();
        List<Integer> listJ = new ArrayList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    listI.add(i);
                    listJ.add(j);
                }
            }
        }
        return getMinSum(listI) + getMinSum(listJ);
    }
    public int getMinSum(List<Integer> list) {
        Collections.sort(list);
        int point = list.get(list.size() / 2);
        int sum = 0;
        for(int num: list) {
            sum += Math.abs(num - point);
        }
        return sum;
    }
}



