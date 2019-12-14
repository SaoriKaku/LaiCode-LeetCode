
/*
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/


// method 1: 2D array
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[][] pattern = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        // 0: not visited; 1: visited: from 1 to 0; 2: visited: from 0 to 1
        int[][] visited = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 1) {
                    int count = check(board, i, j, row, col, visited);
                    //System.out.println("count = " + count);
                    if(count < 2 || count > 3) {
                        board[i][j] = 0;
                        visited[i][j] = 1;
                    }    
                }
            }
        }
        //print2DArray(board, row, col);
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 0) {
                    if(check(board, i, j, row, col, visited) == 3) {
                        board[i][j] = 1;
                        visited[i][j] = 2;
                    }
                }
            }
        }
    }
    public int check(int[][] board, int i, int j, int row, int col, int[][] visited) {
        int count = 0;
        for(int k = 0; k < pattern.length; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            if(nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col) {
                if((board[nextRow][nextCol] == 1 && visited[nextRow][nextCol] != 2) || visited[nextRow][nextCol] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    public void print2DArray(int[][] board, int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

