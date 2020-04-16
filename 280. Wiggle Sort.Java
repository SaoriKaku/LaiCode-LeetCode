
/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
*/


// method 1: sort
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    /*
    1 2 3 4 5 6
    swap(2, 3)
    1 3 2 4 5 6
    swap(4, 5)
    1 3 2 5 4 6
    */
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(i + 1 < nums.length) {
                swap(nums, i, i + 1);
            }
            i++;
        }
    }
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


// method 2: array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        for(int i = 0; i <= nums.length - 2; i++) {
            if(i % 2 == 0 && nums[i] > nums[i+1] || i % 2 == 1 && nums[i] < nums[i+1]) {
                swap(nums, i, i + 1);
            }
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}





