
/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/


// method 1: use row[0] col[0] as flag row and flag col
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0 = false;
        boolean col0 = false;
        // check if col0 should be true
        for(int i = 0; i < row; i++) {
            if(matrix[i][0] == 0) {
                col0 = true;
            }
        }
        // check if row0 should be true
        for(int j = 0; j < col; j++) {
            if(matrix[0][j] == 0) {
                row0 = true;
            }
        }
        // search col[0] if col[0] should be all 0
        // search row[0] if row[0] should be all 0
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // start from col[1], because col[0] used to store flag
        for(int i = 1; i < col; i++) {
            if(matrix[0][i] == 0) {
                // set col = 0
                for(int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        // start from row[1], because row[0] used to store flag
        for(int i = 1; i < row; i++) {
            if(matrix[i][0] == 0) {
                // set row = 0
                for(int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        } 
        if(row0) {
            for(int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if(col0) {
            for(int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}




