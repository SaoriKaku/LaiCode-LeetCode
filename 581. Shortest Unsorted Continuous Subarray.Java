
/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/


// method 1: array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        // right: the rightmost index where nums[right] < max, from begin
        // left: the lefttmost index where nums[left] > min, from back
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int left = -1, right = -1;
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            if(nums[i] < max) {
                right = i;
            }
        }
        int min = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if(nums[i] > min) {
                 left = i;
            }
        }
        //System.out.println("left = " + left + ", right = " + right);
        if(left == -1) return 0;
        return right - left + 1;
    }
}


// method 2: array
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }
        int[] sorted = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
        }
        Arrays.sort(sorted);
        int left = 0;
        for(left = 0; left < nums.length; left++) {
            if(nums[left] != sorted[left]) {
                break;
            }
        }
        int right = nums.length - 1;
        for(right = nums.length - 1; right >= 0; right--) {
            if(nums[right] != sorted[right]) {
                break;
            }
        }
        return right - left + 1 >= 0 ? right - left + 1 : 0;
    }
}






