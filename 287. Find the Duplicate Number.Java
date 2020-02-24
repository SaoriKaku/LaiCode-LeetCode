
/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:
Input: [1,3,4,2,2]
Output: 2

Example 2:
Input: [3,1,3,4,2]
Output: 3

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/


// method 1: count values in nums no more than target
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        /*
        1 3 4 2 2
        l = 1, r = 4, mid = 2
        count(<=2) = 3 > 2, r = mid = 2
        l = 1, r = 2, 
        */
        int left = 1, right = nums.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(countNoMoreThanTarget(nums, mid) > mid) {
                right = mid;
            }
            else {
                left = mid;
            }
        }
        return countNoMoreThanTarget(nums, left) > left ? left : right;
    }
    private int countNoMoreThanTarget(int[] array, int target) {
        int count = 0;
        for(int num: array) {
            if(num <= target) {
                count++;
            }
        }
        return count;
    }
}


