
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
*/


// method 1: DP
// time complexity: O(kn), space complexity: O(n)
class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] result = new int[nums.length];
        //result[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
                if(result[j] == 0) {
                    result[j] = result[i] + 1;
                }
            }
        }
        return result[nums.length - 1];
    }
}