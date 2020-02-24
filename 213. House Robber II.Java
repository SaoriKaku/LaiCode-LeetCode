
/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.

Example 2:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/


// method 1: DP(1D)
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if(n == 3) {
            int temp = Math.max(nums[0], nums[1]);
            return Math.max(temp, nums[2]);
        }
        int a = findMax(nums, 0, n - 1);
        int b = findMax(nums, 1, n - 1);
        //System.out.println("a = " + a + ", b = " + b);
        return Math.max(a, b);
    }
    public int findMax(int[] nums, int start, int length) {
        int[] rob = new int[length];
        rob[0] = nums[start];
        rob[1] = Math.max(nums[start], nums[start + 1]);
        for(int i = 2; i < length; i++) {
            rob[i] = Math.max(rob[i - 1], rob[i - 2] + nums[start + i]);
        }
        //System.out.println(Arrays.toString(rob));
        return rob[length - 1];
    }
}





