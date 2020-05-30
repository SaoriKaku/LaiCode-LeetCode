
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


// method 1: backtracking + trune(when add "")
// time complexity: O(2^n + nlogn), space complexity: O(n + n), n = height of tree
class Solution {
    /*
               ""
          /          \ 
         2                   __           i=0, 2
     /      \            /       \
   22        2_x       _2         __      i=1, 2
  / \       /  \      /   \      /  \
222 22_x  2_2  2__  _22   _2_x __2  ___   i=2, 2

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


// method 2: dfs + prune(when add element)
// time complexity: O(n), space complexity: O(height of tree)
class Solution {
/*
               ""
          /          \ 
         2                   __           i=0, 2
     /      \            /       \
   22        2_        _2x        __      i=1, 2
  / \       /  \      /   \      /  \
222 22_  2_2x  2__  _22   _2_ __2x  ___   i=2, 2
*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> curr = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, 0, visited, curr, result);
        return result;
    }
    public void dfs(int[] nums, int index, boolean[] visited, List<Integer> curr, List<List<Integer>> result) {
        if(index == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        //System.out.println("index = " + index + ", curr = " + curr);
        if(index == 0 || visited[index - 1] || nums[index - 1] != nums[index]) {
            curr.add(nums[index]);
            visited[index] = true;
            dfs(nums, index + 1, visited, curr, result);
            visited[index] = false;
            curr.remove(curr.size() - 1);
        }
        /* xx, x2, 2x, 22, 中间两种情况会产生重复
        判断是2x就return，因为后面会有x2产生一样的subset
        */
        dfs(nums, index + 1, visited, curr, result);
    }
}



