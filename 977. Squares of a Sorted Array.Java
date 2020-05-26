
/*
Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

Example 1:
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

Example 2:
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 
Note:
1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.
*/


// method 1: two pointers
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[] sortedSquares(int[] A) {
        if(A == null || A.length == 0) {
            return A;
        }
        int[] result = new int[A.length];
        int left = 0, right = A.length - 1;
        int index = A.length - 1;
        while(left <= right) {
            if(Math.abs(A[left]) < Math.abs(A[right])) {
                result[index--] = A[right] * A[right];
                right--;
            }
            else {
                result[index--] = A[left] * A[left];
                left++;
            }
        }
        return result;
    }
}


