
/*
Given an unsorted array of integers, find the number of longest increasing subsequence.
Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].

Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
*/


// method 1: DP(1D)
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, maxLength = 0, maxCount = 0;
        int[] len = new int[n];
        int[] count = new int[n];
        for(int i = 0; i < n; i++) {
            len[i] = count[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(len[j] + 1 == len[i]) {
                        count[i] += count[j];
                    }
                    else if(len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if(len[i] == maxLength) {
                maxCount += count[i];
            }
            else if(len[i] > maxLength) {
                maxLength = len[i];
                maxCount = count[i];
            }
        }
        return maxCount;
    }
}




