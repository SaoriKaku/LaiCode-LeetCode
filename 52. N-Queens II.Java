
/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/


// method 1: DFS
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int totalNQueens(int n) {
        if(n <= 0) {
            return 0;
        }
        int[] count = new int[1];
        int[] curr = new int[n];
        //List<Integer> curr = new ArrayList<>();
        nQueensHelper(0, n, curr, count);
        return count[0];
    }
    public void nQueensHelper(int row, int n, int[] curr, int[] count) {
        if(row == n) {
            count[0]++;
            return;
        }
        for(int col = 0; col < n; col++) {
            if(check(row, col, curr)) {
                int prev = curr[row];
                curr[row] = col;
                nQueensHelper(row + 1, n, curr, count);
                curr[row] = prev;
            }
        }
    }
    public boolean check(int row, int col, int[] curr) {
        //System.out.println("row = " + row + ", curr.size() = " + curr.size());
        for(int i = 0; i < row; i++) {
            if(curr[i] == col || row - i == Math.abs(curr[i] - col)) {
                return false;
            }
        }
        return true;
    }
}



