
/*
Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.

Example:
Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/


// method 1: 2D Array
// time complexity: O(m * n * k), space complexity: O(m * n)
class Solution {
    /*
    result[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1] + A[0][2] * B[2][1];
    result[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0] + A[1][2] * B[2][0]; 
    k = 0; k < col; k++
    result[i][j] = A[i][0] * B[0][j] +
                   A[i][1] * B[1][j] + 
                   A[i][2] * B[2][j];
    */
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length;
        int colA = A[0].length;
        int colB = B[0].length;
        int[][] result = new int[rowA][colB];
        for(int i = 0; i < rowA; i++) {
            for(int j = 0; j < colB; j++) {
                for(int k = 0; k < colA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}


