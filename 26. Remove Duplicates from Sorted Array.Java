
/*
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Given nums = [1,1,2],
Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the returned length.

Example 2:
Given nums = [0,0,1,1,1,2,2,3,3,4],
Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
It doesn't matter what values are set beyond the returned length.
*/


// method 1: fast slow pointers
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int s = 0;
        int f = 1;
        while(f < nums.length) {
            if(nums[s] == nums[f]) {
                f++;
            }
            else {
                swap(nums, s + 1, f);
                s++;
                f++;
            }
        }
        //System.out.println(Arrays.toString(nums));
        //System.out.println("s = " + s + ", f = " + f);
        return s + 1;
    }
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


// method 2: two pointers
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int s = 1;
        int f = 1;
        while(f < nums.length) {
            if(nums[f] != nums[f - 1]) {
                nums[s++] = nums[f++];
            }
            else {
                f++;
            }
        }
        //System.out.println(Arrays.toString(nums));
        //System.out.println("s = " + s + ", f = " + f);
        return s;
    }
}




