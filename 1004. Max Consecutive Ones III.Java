
/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1s. 

Example 1:
Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 
Note:
1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 
*/


// method 1: two pointers, traverse left
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0, result = 0, n = A.length, count = 0;
        while(left < n) {
            while(right < n && count <= K) {
                if(A[right] == 0) {
                    if(count == K) {
                        break;
                    }
                    else {
                        count++;
                    }
                }
                right++;
            }
            result = Math.max(result, right - left);
            if(A[left++] == 0) {
                count--;
            }
            //System.out.println("left = " + left + ", right = " + right);
        }
        return result;
    }
}


