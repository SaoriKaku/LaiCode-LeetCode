
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
*/


// method 1: binary search with duplicate
// time complexity: O(logn) -> O(n), space complexity: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return true;
            }
            else if(nums[left] == nums[mid]) {
                left++;
            }
            else if(nums[mid] == nums[right]) {
                right--;
            }
            else if(nums[left] < nums[mid]) {
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        if(nums[left] == target || nums[right] == target) return true;
        return false;
    }
}


// method 2: optimized method 1, binary search with duplicate
// time complexity: O(logn) -> O(n), space complexity: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int left = 0, right = nums.length - 1;
        while(left + 1 < right) {
            if(left + 1 < nums.length && nums[left + 1] == nums[left]) {
                left++;
            }
            else if(right - 1 >= 0 && nums[right] == nums[right - 1]) {
                right--;
            }
            else {
                int mid = left + (right - left) / 2;
                if(nums[mid] == target) {
                    return true;
                }
                else if(nums[left] <= nums[mid]) {
                    if(nums[left] <= target && target < nums[mid]) {
                        right = mid - 1;
                    }
                    else {
                        left = mid + 1;
                    }
                }
                else {
                    if(nums[mid] < target && target <= nums[right]) {
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
            }
        }
        if(nums[left] == target) return true;
        if(nums[right] == target) return true;
        return false;
    }
}




