
/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/


// method 1: dfs
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        permuteUniqueHelper(nums, 0, result);
        return result;
    }
    public void permuteUniqueHelper(int[] nums, int index, List<List<Integer>> result) {
        if(index == nums.length) {
            List<Integer> solu = new ArrayList<>();
            for(int curr: nums) {
                solu.add(curr);
            }
            result.add(solu);
        }
        Set<Integer> used = new HashSet<>();
        for(int i = index; i < nums.length; i++) {
            if(used.add(nums[i])) {
                swap(nums, i, index);
                permuteUniqueHelper(nums, index + 1, result);
                swap(nums, i, index);
            }
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}




