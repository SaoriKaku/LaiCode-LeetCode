
/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/


// method 1: 
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int count = 1;
        while(left <= right && top <= bottom) {
            for(int i = left; i <= right; i++) {
                result[top][i] = count++;
            }
            top++;
            for(int i = top; i <= bottom; i++) {
                result[i][right] = count++;
            }
            right--;
            for(int i = right; i >= left; i--) {
                result[bottom][i] = count++;
            }
            bottom--;
            for(int i = bottom; i >= top; i--) {
                result[i][left] = count++;
            }
            left++;
        }
        return result;
    }
}


