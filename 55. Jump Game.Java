
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
*/


// method 1: 1 round 
// Assumption: n = the length of nums, k = max value in nums
// time complexity: O(kn), space complexity: O(n)
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean[] result = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++) {
            if(!result[i] && i != 0) {
                //System.out.println(Arrays.toString(result));
                return false;
            }
            result[i] = true;
            for(int j = i; j <= i + nums[i] && j < nums.length; j++) {
                result[j] = true;
            }
        }
        //System.out.println(Arrays.toString(result));
        return result[nums.length - 1];
    }
}


// method 2: 1 round
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int currMaxIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(currMaxIndex < i) {
                return false;
            }
            currMaxIndex = Math.max(currMaxIndex, nums[i] + i);
        }
        return currMaxIndex >= nums.length - 1;
    }
}





