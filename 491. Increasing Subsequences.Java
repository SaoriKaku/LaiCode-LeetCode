
/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Constraints:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
*/


// method 1: dfs
// time complexity: O(n!), space complexity: O(n)
class Solution {
    /*
              root
         /   |    |   \
        4    6    7    7x
      / |\   |\   |
     6  7 7x 7 7x 7
    /|  |    |
   7 7x 7    7
   |
   7
   46, 467, 4677, 47, 477, 67, 677, 77
    */
    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        dfs(nums, 0, curr, result);
        return result;
    }
    public void dfs(int[] nums, int index, List<Integer> curr, List<List<Integer>> result) {
        if(curr.size() > 1) {
            result.add(new ArrayList<>(curr));
        }
        Set<Integer> set = new HashSet<>();
        for(int i = index; i < nums.length; i++) {
            if(curr.size() == 0 || nums[i] >= curr.get(curr.size() - 1)) {
                if(set.add(nums[i])) {
                    curr.add(nums[i]);
                    dfs(nums, i + 1, curr, result);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    }
}




