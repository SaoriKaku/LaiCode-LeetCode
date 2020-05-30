
/*
Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
You may return any answer array that satisfies this condition.

Example 1:
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 
Note:
1 <= A.length <= 5000
0 <= A[i] <= 5000
*/


// method 1: two pointers
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    3  1  2  4
    left     right
    */
    public int[] sortArrayByParity(int[] A) {
        if(A == null || A.length == 0) {
            return A;
        }
        int left = 0, right = A.length - 1;
        while(left < right) {
            if(A[left] % 2 == 0) {
                left++;
            }
            else if(A[right] % 2 == 1) {
                right--;
            }
            else {
                swap(A, left++, right--);
            }
        }
        return A;
    }
    public void swap(int[] A, int left, int right) {
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }
}


