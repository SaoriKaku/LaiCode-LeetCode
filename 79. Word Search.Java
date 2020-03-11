
/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

/*
for y in 1...n
	for x in 1...m
		if search(x, y, 0): return true
return false;

search(x, y, d)
	if out_of_bound(x, y): return false
	if word[d] != grid[y][x]: return false
	if d == word.length - 1: return true
	return 
	search(x - 1, y, d + 1)
	or search(x + 1, y, d + 1)
	or search(x, y - 1, d + 1)
	or search(x, y + 1, d + 1)

time complexity: search: O(4^l), l = len(word)
total: O(m * n * 4^l)
space complexity: O(l)
*/
// 这个去重的方法非常巧妙：在这一轮先改变，然后回来的时候再重设为原来的值
class Solution {
    public int[][] pattern = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col;j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(board, i, j, row, col, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, int row, int col, String word, int index) {
        if(i < 0 || i >= row || j < 0 || j >= col) {
            return false;
        }
        if(board[i][j] != word.charAt(index)) {
            return false;
        }
        if(index == word.length() - 1) {
            return true;
        }
        char curr = board[i][j];
        board[i][j] = '1';
        boolean found = dfs(board, i + 1, j, row, col, word, index + 1) || dfs(board, i - 1, j, row, col, word, index + 1) || dfs(board, i, j + 1, row, col, word, index + 1) || dfs(board, i, j - 1, row, col, word, index + 1);
        board[i][j] = curr;
        return found;
    }
}


// method 1: dfs, word.length = l, row = m, col = n
// time complexity: O(m * n * 4^l), space complexity: O(m * n + l)
class Solution {
    public int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;
        int[][] visited = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(dfs(board, visited, row, col, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, int[][] visited, int row, int col, int x, int y, int index, String word) {
        if(index == word.length()) {
            return true;
        }
        if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] == 1 || board[x][y] != word.charAt(index)) {
            return false;
        }
        visited[x][y] = 1;
        for(int i = 0; i < 4; i++) {
            int nextX = x + pattern[i][0];
            int nextY = y + pattern[i][1];
            if(dfs(board, visited, row, col, nextX, nextY, index + 1, word)) {
                return true;
            }
        }
        visited[x][y] = 0;
        return false;
    }
}

