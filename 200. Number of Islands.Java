
/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
*/

// method 1: BFS, HashSet<Cell>
class Solution {
    public static final int[][] pattern = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public class Cell {
        int row; 
        int col;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }
            if(! (obj instanceof Cell)) {
                return false;
            }
            Cell other = (Cell)obj;
            return this.row == other.row && this.col == other.col;
        }
        public int hashCode() {
            return this.row * 31 + this.col;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        HashSet<Cell> set = new HashSet<>();
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                Cell curr = new Cell(i, j);
                if(set.contains(curr)) {
                    continue;
                }
                if(grid[i][j] == '0') {
                   set.add(curr);
                   continue;
                }
                //System.out.println("i = " + i + " j = " + j);
                count++;
                Queue<Cell> queue = new LinkedList<>();
                queue.offer(curr);
                while(!queue.isEmpty()) {
                    Cell pollCell = queue.poll();
                    int pollRow = pollCell.row;
                    int pollCol = pollCell.col;
                    for(int k = 0; k < 4; k++) {
                        int nextRow = pollRow + pattern[k][0];
                        int nextCol = pollCol + pattern[k][1];
                        if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col) {
                            Cell next = new Cell(nextRow, nextCol);
                            if(grid[nextRow][nextCol] == '1' && !set.contains(next)) {
                                queue.offer(next);
                            }
                            set.add(next);
                        }
                    }// end for k
                }// end while
            }// end for j
        }// end for i
        return count;
    }
}


// method 2: BFS, 不用HashSet, 让grid[i][j] = 0, 最终grid[i][j] == 0
class Solution {
    public static final int[][] pattern = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public class Cell {
        int row; 
        int col;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                Cell curr = new Cell(i, j);
                if(grid[i][j] == '0') {
                   continue;
                }
                count++;
                Queue<Cell> queue = new LinkedList<>();
                queue.offer(curr);
                while(!queue.isEmpty()) {
                    Cell pollCell = queue.poll();
                    int pollRow = pollCell.row;
                    int pollCol = pollCell.col;
                    for(int k = 0; k < 4; k++) {
                        int nextRow = pollRow + pattern[k][0];
                        int nextCol = pollCol + pattern[k][1];
                        if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col) {
                            Cell next = new Cell(nextRow, nextCol);
                            if(grid[nextRow][nextCol] == '1') {
                                queue.offer(next);
                            }
                            grid[nextRow][nextCol] = '0';
                        }
                    }// end for k
                }// end while
            }// end for j
        }// end for i
        return count;
    }
}


// method 3: DFS，速度超级快
class Solution {
    public static final int[][] pattern = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[] count = new int[1];
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == '0') {
                    continue;
                }
                bfs(grid, row, col, i, j);
                count[0]++;
            }// end for j
        }// end for i
        return count[0];
    }
    public void bfs(char[][] grid, int row, int col, int i, int j) {
        if(i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for(int k = 0; k < 4; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            bfs(grid, row, col, nextRow, nextCol);
        }
    }
}


