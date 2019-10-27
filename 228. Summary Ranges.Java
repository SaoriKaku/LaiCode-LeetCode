
/*
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
*/

// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        int left = 0;
        int right = 0;
        while(right < nums.length) {
            if(right + 1 < nums.length && nums[right + 1] == nums[right] + 1) {
                right++;
                continue;
            }
            if(left == right) {
                result.add(nums[left] + "");
            }
            else {
                result.add(nums[left] + "->" + nums[right]);
            }
            left = right + 1;
            right++;
            //System.out.println(result);
        }
        return result;
    }
}

