
/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6

Note:
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // key: value, value: int[]: {count, firstIndex, lastIndex}
        Map<Integer, int[]> map = new HashMap<>();
        int maxCount = 0, minLength = nums.length;
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int[] values = map.get(nums[i]);
                values[0]++;
                values[2] = i;
            }
            else {
                map.put(nums[i], new int[]{1, i, i});
            }
            // update minLength
            int[] values = map.get(nums[i]);
            if(values[0] > maxCount) {
                maxCount = values[0];
                minLength = values[2] - values[1] + 1;
            }
            else if(values[0] == maxCount) {
                minLength = Math.min(minLength, values[2] - values[1] + 1);
            }
        }
        return minLength;
    }
}


