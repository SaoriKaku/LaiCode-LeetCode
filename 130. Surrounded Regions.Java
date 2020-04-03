
/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X

Explanation:
Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/


// method 1: dfs
// time compelxity: O(m * n), space complextiy: O(m * n)
class Solution {
    public int[][] pattern = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        if(board == null || board.length <= 2 || board[0].length <=2) {
            return;
        }
        int row = board.length, col = board[0].length;
        // 1: board[i][j] = 'O'; 0: board[i][j] = 'X'
        int[][] isLetterO = new int[row][col];
        // first row, last row
        for(int j = 1; j < col - 1; j++) {
            if(board[0][j] == 'O') {
                dfs(board, isLetterO, 1, j, row, col);
            }
            if(board[row-1][j] == 'O') {
                dfs(board, isLetterO, row - 2, j, row, col);
            }
        }
        // first col, last col
        for(int i = 1; i < row - 1; i++) {
            if(board[i][0] == 'O') {
                dfs(board, isLetterO, i, 1, row, col);
            }
            if(board[i][col-1] == 'O') {
                dfs(board, isLetterO, i, col - 2, row, col);
            }
        }
        for(int i = 1; i < row - 1; i++) {
            for(int j = 1; j < col - 1; j++) {
                if(isLetterO[i][j] == 0) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void dfs(char[][] board, int[][] isLetterO, int x, int y, int row, int col) {
        // 1 <= x <= row - 2, 1 <= y <= col - 2
        if(x < 1 || x > row - 2 || y < 1 || y > col - 2 || board[x][y] == 'X' || isLetterO[x][y] == 1) {
            return;
        }
        isLetterO[x][y] = 1;
        for(int i = 0; i < 4; i++) {
            int nextX = x + pattern[i][0];
            int nextY = y + pattern[i][1];
            dfs(board, isLetterO, nextX, nextY, row, col);
        }
    }
}



