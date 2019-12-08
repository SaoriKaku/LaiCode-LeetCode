
/*
xGiven a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/


// method 1: backtracking + trune(boolean[] added + sorted)
// time complexity: O(2^n + nlogn), space complexity: O(n + n)
class Solution {
    /*
          ""
       /      \ 
      ""        1        + 1    
     / \       / \
    ""  2     1   12     + 2
   / \ / \   / \ / \
  "" 2 2 22 1 12 12 122  + 2
    */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        boolean[] added = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(0, nums, curr, result, added);
        return result;
    }
    public void dfs(int index, int[] nums, List<Integer> curr, List<List<Integer>> result, boolean[] added) {
        if(index == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        // when nums[index] == nums[index - 1] && added[index - 1]，continue；
        curr.add(nums[index]);
        added[index] = true;
        dfs(index + 1, nums, curr, result, added);
        curr.remove(curr.size() - 1);
        added[index] = false;
        if(index > 0 && nums[index] == nums[index - 1] && added[index - 1]) {
            return;
        }
        dfs(index + 1, nums, curr, result, added);
    }
}



