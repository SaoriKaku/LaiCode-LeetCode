
/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/


// time complexity: O(n), space complexity: O(1)
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int start = 0;
        int curr = 0;
        while(curr < nums.length) {
            if(nums[curr] == 0) {
               curr++; 
            }
            else {
                nums[start++] = nums[curr++];
            }
        }
        while(start < nums.length) {
            nums[start++] = 0;
        }
    }
}



