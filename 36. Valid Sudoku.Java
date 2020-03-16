
/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
A partially filled sudoku which is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:
Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

Example 2:
Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/


// method 1: HashSet
// time complexity: O(m * n), space complexity: O(9 * 9 * 3)
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Integer>[] row = new HashSet[9];
        Set<Integer>[] col = new HashSet[9];
        Set<Integer>[][] box = new HashSet[3][3];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;
                int curr = (int)(board[i][j] - '0');
                if(row[i] == null) {
                    row[i] = new HashSet<Integer>();
                    row[i].add(curr);
                }
                else {
                    if(!row[i].add(curr)) {
                        //System.out.println("row = " + i + ", curr = " + curr);
                        return false;
                    }
                }
                if(col[j] == null) {
                    col[j] = new HashSet<Integer>();
                    col[j].add(curr);
                }
                else {
                    if(!col[j].add(curr)) {
                        //System.out.println("col = " + j + ", curr = " + curr);
                        return false;
                    }
                }
                int boxRow = i / 3;
                int boxCol = j / 3;
                if(box[boxRow][boxCol] == null) {
                    box[boxRow][boxCol] = new HashSet<Integer>();
                    box[boxRow][boxCol].add(curr);
                }
                else {
                    if(!box[boxRow][boxCol].add(curr)) {
                        //System.out.println("row = " + i + ", col = " + j);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}



