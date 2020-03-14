
/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/


// method 1: DP
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    result[i], max product from index = 0 to index = i
               min product from index = 0 to index = i
    min = min(min, preMax * currNum, preMin * currNum);
    max = max(max, preMax * currNum, preMin * currNum);
    */
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] maxProduct = new int[nums.length];
        int[] minProduct = new int[nums.length];
        int max = nums[0];
        maxProduct[0] = nums[0];
        minProduct[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            maxProduct[i] = Math.max(maxProduct[i-1] * nums[i], minProduct[i-1] * nums[i]);
            maxProduct[i] = Math.max(nums[i], maxProduct[i]);
            minProduct[i] = Math.min(maxProduct[i-1] * nums[i], minProduct[i-1] * nums[i]);
            minProduct[i] = Math.min(nums[i], minProduct[i]);
            max = Math.max(max, maxProduct[i]);
        }
        return max;
    }
}


// method 1: DP
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, result = nums[0];
        int[] positiveProduct = new int[n]; 
        int[] negativeProduct = new int[n];
        if(nums[0] >= 0) {
            positiveProduct[0] = nums[0];
            negativeProduct[0] = 1;
        }
        else {
            negativeProduct[0] = nums[0];
            positiveProduct[0] = 1;
        }
        for(int i = 1; i < n; i++) {
            if(nums[i] < 0) {
                negativeProduct[i] = Math.min(nums[i], positiveProduct[i-1] * nums[i]);
                positiveProduct[i] = negativeProduct[i-1] * nums[i];
            }
            else {
                positiveProduct[i] = Math.max(nums[i], positiveProduct[i-1] * nums[i]);
                negativeProduct[i] = negativeProduct[i-1] * nums[i];
            }
            result = Math.max(result, positiveProduct[i]);
        }
        return result;
    }
}

