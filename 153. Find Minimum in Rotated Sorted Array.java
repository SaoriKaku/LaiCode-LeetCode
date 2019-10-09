
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/


class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right) { // 注意这里没有等号，下面也都没有等号
            //System.out.println("left = " + left + " right = " + right);
            
            int mid = left + (right - left) / 2;
            // the left part is sorted
            if(nums[left] < nums[mid]) {
                if(nums[left] < nums[right]) {
                    return nums[left];
                }
                else {
                    left = mid;
                }
            }
            // the right part is sorted
            else {
                if(nums[mid] < nums[mid + 1]) {
                    right = mid;
                }
                else {
                    return nums[mid + 1];
                }
            }
        }
        return nums[left];
    }
}


