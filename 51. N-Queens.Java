
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/


class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if(n <= 0) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        nQueenHelper(n, 0, curr, result);
        return result;
    }
    public void nQueenHelper(int n, int row, List<Integer> curr, List<List<String>> result) {
        if(row == n) {
            //System.out.println("test");
            List<String> solu = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringBuilder currRow = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if(j == curr.get(i)) {
                        currRow.append("Q");
                    }
                    else {
                        currRow.append(".");
                    }
                }
                solu.add(String.valueOf(currRow));
            }
            result.add(solu);
        }
        // for(int i = 0; i < n; i++) {
        //     System.out.print(curr.get(i) + " ");
        // }
        // System.out.println();
        for(int col = 0; col < n; col++) {
            if(check(curr, row, col)) {
                curr.add(col);
                nQueenHelper(n, row + 1, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }
    // index == row number, curr[index] == col number
    public boolean check(List<Integer> curr, int row, int col) {
       for(int i = 0; i < curr.size(); i++) {
           if(curr.get(i) == col || Math.abs(i - row) == Math.abs(curr.get(i) - col)) {
               return false;
           }
       }
        return true;
    }
}




