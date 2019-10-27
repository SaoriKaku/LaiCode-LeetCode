
/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/


// time complexity: O(2^n), space complexity:O(1)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        dfs(nums, 0, curr, result);
        return result;
    }
    public void dfs(int[] nums, int index, List<Integer> curr, List<List<Integer>> result) {
        if(index == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        curr.add(nums[index]);
        dfs(nums, index + 1, curr, result);
        curr.remove(curr.size() - 1);
        
        dfs(nums, index + 1, curr, result);
    }
}



