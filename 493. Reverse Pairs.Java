
/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
*/


// method 1: mergesort
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] helper = new int[nums.length];
        return mergeSort(nums, helper, 0, nums.length - 1);
    }
    public int mergeSort(int[] nums, int[] helper, int left, int right) {
        if(left >= right) return 0;
        int mid = left + (right - left) / 2;
        int result = 0;
        result += mergeSort(nums, helper, left, mid);
        result += mergeSort(nums, helper, mid + 1, right);
        result += merge(nums, helper, left, mid, right);
        return result;
    }
    public int merge(int[] nums, int[] helper, int left, int mid, int right) {
        for(int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }
        int count = 0, leftBound = left, rightBound = mid + 1;
        // count i < j && nums[i] > 2 * nums[j]
        while(leftBound <= mid && rightBound <= right) {
            if((long)helper[leftBound] > (long)2 * helper[rightBound]) {
                count += mid - leftBound + 1;
                rightBound++;
            }
            else {
                leftBound++;
            }
        }
        // sort
        leftBound = left;
        rightBound = mid + 1;
        int index = left;
        while(leftBound <= mid && rightBound <= right) {
            if(helper[leftBound] < helper[rightBound]) {
                nums[index++] = helper[leftBound++];
            }
            else {
                nums[index++] = helper[rightBound++];
            }
        }
        while(leftBound <= mid) {
            nums[index++] = helper[leftBound++];
        }
        return count;
    }
}



