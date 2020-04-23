
/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
method 1: 
start from grid[i][j] == 0, int[][] result = new int[row][col];
int[][] visitBuilding = new int[row][col]; vs. int countBuilding = 0;

boolean[][] visited = new boolean[row][col];
Queue<int[]> queue = new ArrayDeque<>();
time complexity: O(m^2 * n^2), space complexity: O(m * n)
*/
class Solution {
    public int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length; 
        int[][] result = new int[row][col];
        int[][] visitBuilding = new int[row][col];
        int countBuilding = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    countBuilding++;
                }
                else if(grid[i][j] == 0) {
                    // used to update result[][], means distance from all buildings to point[i][j]
                    int sum = 0; 
                    int count = 0;
                    // visited[i][j] == 0, not visited, otherwise, visited
                    boolean[][] visited = new boolean[row][col];
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for(int m = 0; m < size; m++) {
                            int[] curr = queue.poll();
                            int currRow = curr[0];
                            int currCol = curr[1];
                            if(grid[currRow][currCol] == 1) {
                                sum = sum + count;
                                visitBuilding[i][j]++;
                            }
                            // only if grid[i][j] == 0, we could go through
                            if(grid[currRow][currCol] == 0) {
                                for(int n = 0; n < 4; n++) {
                                    int nextRow = currRow + pattern[n][0];
                                    int nextCol = currCol + pattern[n][1];
                                    // pattern = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                                    if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && !visited[nextRow][nextCol]) {
                                        visited[nextRow][nextCol] = true;
                                        queue.offer(new int[] {nextRow, nextCol});
                                    }
                                }// end for pattern
                            }// end if 
                        }// end for layer
                        count++;
                    }// end while
                    result[i][j] = sum;
                }// end if
            }// end for
        }// end for
        
        // traverse to find the minimum one to return
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 0 && visitBuilding[i][j] == countBuilding) {
                    min = Math.min(result[i][j], min);
                }
            }
        }
        if(min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
}


/*
method 2: 
start from grid[i][j] == 0, int[][] distFromBuildings = new int[row][col];
int[][] visitLand = new int[row][col]; vs. int countBuilding = 0;

boolean[][] visited = new boolean[row][col];
Queue<int[]> queue = new ArrayDeque<>();
time complexity: O(m^2 * n^2), space complexity: O(m * n)
*/
class Solution {
    public int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length; 
        int[][] distFromBuildings = new int[row][col];
        int[][] visitBuilding = new int[row][col];
        int countBuilding = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    countBuilding++;
                    // used to update result[][], means distance from all buildings to point[i][j]
                    int count = 1;
                    // visited[i][j] == 0, not visited, otherwise, visited
                    boolean[][] visited = new boolean[row][col];
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[] {i, j});
                    visited[i][j] = true;
                    
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for(int m = 0; m < size; m++) {
                            int[] curr = queue.poll();
                            // only if grid[i][j] == 0, we could go through
                            for(int n = 0; n < 4; n++) {
                                int nextRow = curr[0] + pattern[n][0];
                                int nextCol = curr[1] + pattern[n][1];
                                // pattern = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
                                if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && !visited[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                                    visitBuilding[nextRow][nextCol]++;
                                    visited[nextRow][nextCol] = true;
                                    distFromBuildings[nextRow][nextCol] += count;
                                    queue.offer(new int[] {nextRow, nextCol});
                                }
                            }// end for pattern
                        }// end for layer
                        count++;
                    }// end while
                }// end if
            }// end for
        }// end for
        
        // traverse to find the minimum one to return
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                //System.out.print(distFromBuildings[i][j] + " " + visitLand[i][j] + ", ");
                if(grid[i][j] == 0 && visitBuilding[i][j] == countBuilding) {
                    min = Math.min(distFromBuildings[i][j], min);
                }
            }
            //System.out.println();
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}


/*
method 3: bfs, from each building, buildng count = k
time complexity: O(k * m * n), space complexity: O(m * n)
*/
class Solution {
    private int[][] pattern = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        // totol distance from a position where grid[i][j] == 0
        int[][] dist = new int[row][col];
        // how many buildings(grid[i][j] == 1) this position(grid[i][j] == 0) has been reached
        int[][] reachedBuilding = new int[row][col];
        int totalBuildingCount = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    totalBuildingCount++;
                    int[][] visited = new int[row][col];
                    visited[i][j] = 1;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    int layer = 0;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        layer++;
                        for(int l = 0; l < size; l++) {
                            int[] currNode = queue.poll();
                            int x = currNode[0];
                            int y = currNode[1];
                            for(int k = 0; k < pattern.length; k++) {
                                int nextX = x + pattern[k][0];
                                int nextY = y + pattern[k][1];
                                if(nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && visited[nextX][nextY] == 0 && grid[nextX][nextY] == 0) {
                                    reachedBuilding[nextX][nextY] += 1;
                                    dist[nextX][nextY] += layer;
                                    visited[nextX][nextY] = 1;
                                    queue.offer(new int[]{nextX, nextY});
                                }
                            }
                        }
                    }
                }
            }
        }
        //print2DArray(dist);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 0 && reachedBuilding[i][j] == totalBuildingCount) {
                    min = Math.min(min, dist[i][j]);
                } 
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    public void print2DArray(int[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}




