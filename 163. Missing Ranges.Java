
/*
Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:
Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
*/


// method 1: array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if(nums == null || lower > upper) {
            return result;
        }
        for(int i = 0; i <= nums.length; i++) {
            long start = i == 0 ? lower : (long)nums[i - 1] + 1;
            long end = i == nums.length ? upper: (long)nums[i] - 1;
            if(start == end) {
                result.add(String.valueOf(start));
            }
            else if(end > start) {
                String range = start + "->" + end;
                result.add(range);
            }
        }
        return result;
    }
}


