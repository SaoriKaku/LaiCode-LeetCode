
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
A sudoku puzzle...
...and its solution numbers marked in red.

Note:
The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.
*/


// method 1: dfs
// time complexity: O(9^81 * 27), space complexity: O(9 * 9)
class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }
    public boolean dfs(char[][] board, int x, int y) {
        while(x < 9) {
            if(board[x][y] == '.') {
                break;
            }
            y += 1;
            if(y == 9) {
                y = 0;
                x += 1;
            }
        }
        if(x == 9) {
            return true;
        }
        for(int i = 1; i < 10; i++) {
            if(isValid(board, x, y, i)) {
                board[x][y] = (char)(i + '0');
                // jump board[x][y] = number
                if(dfs(board, x, y)) {
                    return true;
                }
                board[x][y] = '.';
            }
        }
        return false;
    }
    public boolean isValid(char[][] board, int x, int y, int val) {
        char curr = (char)('0' + val);
        for(int i = 0; i < 9; i++) {
            if(board[x][i] == curr || board[i][y] == curr) {
                return false;
            }
        }
        int boxX = x / 3 * 3;
        int boxY = y / 3 * 3;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[boxX + i][boxY + j] == curr) {
                    return false;
                }
            }
        }
        return true;
    }
}





