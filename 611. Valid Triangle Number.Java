
/*
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
*/


// method 1: binary search
// time complexity: O(n^2 & logn), space complexity: O(logn)
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        for(int i = 0; i <= nums.length - 3; i++) {
            for(int j = i + 1; j <= nums.length - 2; j++) {
                int index = binarySearch(nums, j + 1, nums.length - 1, nums[i] + nums[j]);
                if(index != -1) {
                    result += index - j;
                }
            }
        }
        return result;
    }
    // return the index of the biggest number smaller than target
    public int binarySearch(int[] nums, int left, int right, int target) {
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        if(nums[right] < target) return right;
        if(nums[left] < target) return left;
        return -1;
    }
}


// method 2: array
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        // the largest edge is determined
        for(int i = 2; i < nums.length; i++) {
            int left = 0, right = i - 1;
            while(left < right) {
                // right is determined, the third number from left to right - 1
                if(nums[left] + nums[right] > nums[i]) {
                    result += right - left;
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return result;
    }
}




