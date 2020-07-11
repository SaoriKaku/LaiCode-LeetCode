
/*
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
Note that the subarray needs to be non-empty after deleting one element.

Example 1:
Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.

Example 2:
Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.

Example 3:
Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 
Constraints:
1 <= arr.length <= 10^5
-10^4 <= arr[i] <= 10^4
*/


// method 1: DP(1D)
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int maximumSum(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] leftMaxSum = new int[n];
        leftMaxSum[0] = arr[0];
        int max = arr[0];
        for(int i = 1; i < n; i++) {
            leftMaxSum[i] = Math.max(arr[i], leftMaxSum[i-1] + arr[i]);
            max = Math.max(max, leftMaxSum[i]);
        }
        int[] rightMaxSum = new int[n];
        rightMaxSum[n-1] = arr[n-1];
        for(int i = n - 2; i >= 0; i--) {
            rightMaxSum[i] = Math.max(arr[i], rightMaxSum[i+1] + arr[i]);
        }
        for(int i = 1; i < n - 1; i++) {
            max = Math.max(max, leftMaxSum[i-1] + rightMaxSum[i+1]);
        }
        return max;
    }
}


