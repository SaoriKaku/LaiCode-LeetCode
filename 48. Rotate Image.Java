
/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/


// method 1: in place
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public void rotate(int[][] matrix) {
       if(matrix == null || matrix.length == 0) {
           return;
       }
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            int firstLayer = i;
            int lastLayer = n - 1 - i;
            /*
            save right;
            right = top;
            top = left;
            left = bottom;
            bottom = right;
            */
            for(int j = firstLayer; j < lastLayer; j++) {
                /*
                row top: matrix[firstLayer][j] -> left to right
                row bottom: matrix[lastLayer][lastLayer - offset] -> right to left
                col left: matrix[lastLayer - offset][firstLayer] -> bottom up
                col right: matrix[j][lastLayer] -> top down
                */
                int offset = j - firstLayer;
                int temp = matrix[j][lastLayer];
                matrix[j][lastLayer] = matrix[firstLayer][j];
                matrix[firstLayer][j] = matrix[lastLayer - offset][firstLayer];
                matrix[lastLayer - offset][firstLayer] = matrix[lastLayer][lastLayer - offset];
                matrix[lastLayer][lastLayer - offset] = temp;
            }
        }
    }
}


// method 2: swap by diagonal, swap by middle column
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        // swap by diagonal 
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // swap by middle column
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n / 2; j ++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}

