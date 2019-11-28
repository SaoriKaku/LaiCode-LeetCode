
/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
*/


// method 1: count 
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums[0];
        int count = 0;
        for(int curr: nums) {
            if(curr == result) {
                count++;
            }
            else {
                count--;
                if(count < 0) {
                    result = curr;
                    count = 1;
                }
            }
        }
        return result;
    }
}



