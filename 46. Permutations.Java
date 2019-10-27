
/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/


// time complexity: O(n!), space complexity: O(1)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        permuteHelper(nums, 0, result);
        return result;
    }
    public void permuteHelper(int[] nums, int index, List<List<Integer>> result) {
        if(index == nums.length) {
            List<Integer> curr = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) {
                curr.add(nums[i]);
            }
            result.add(curr);
        }
        for(int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            permuteHelper(nums, index + 1, result);
            swap(nums, index, i);
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

