
/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/


// method 1: bit + 2round
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[] {};
        }
        int xor = 0;
        for(int curr: nums) {
            xor ^= curr;
        }
        int mask = 1;
        while((xor & mask) == 0) {
            mask = mask << 1;
        }
        // 找到从右边第一个bit为1
        int a = 0;
        int b = 0;
        for(int curr: nums) {
            // curr在bit为1的这一位是0
            if((curr & mask) == 0) {
                a ^= curr;
            }
            // curr在bit为1的这一位是1
            else {
                b ^= curr;
            }
        }
        return new int[] {a, b};
    }
}



