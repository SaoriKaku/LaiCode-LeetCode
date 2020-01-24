
/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/


// method 1: time complexity: O(logn), space complexity: O(1)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = findRange(nums, target, true);
        //System.out.println("leftBound = " + left);
        int right = findRange(nums, target, false);
        //System.out.println("rightBound = " + right);
        return nums[left] == target ? new int[]{left, right} : new int[]{-1, -1};
    }
    public int findRange(int[] nums, int target, boolean leftBound) {
        int left = 0;
        int right = nums.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            //System.out.println("before: left = " + left + ", mid = " + mid + ", right = " + right);
            if(nums[mid] == target) {
                if(leftBound) {
                    right = mid;
                }
                else {
                    left = mid;
                }
            }
            else if(nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
            //System.out.println("after: left = " + left + ", mid = " + mid + ", right = " + right);
        }
        if(leftBound) {
            return nums[left] == target ? left : right;
        }
        else {
            return nums[right] == target ? right : left;
        }
    }
}



