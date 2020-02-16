
/*
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
*/


// method 1: two pointers, traverse right pointer
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right++];
            while (sum >= s) {
                result = Math.min(result, right - left);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}


// method 2: two pointers, traverse left pointer
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        while(left < nums.length) {
            while(right < nums.length && sum < s) {
                sum += nums[right++];
            }
            if(sum >= s) {
                result = Math.min(result, right - left);
            }
            sum -= nums[left++];
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}




