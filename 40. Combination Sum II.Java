
/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/


/* [1, 1, 2, 2]
    
               [ ]
            /   |   \
         i=0   i=1   i=2
          1     1    2         index = 0
         /  \   |
       i=1 i=2  i=2
       11  12   12             index = 1
        |
       i=2
       112                     index = 2
         
                           [ ]
              /         |       |       \
           i=0         i=1      i=2      i=3
            1           1        2        2        index = 0
        /   |   \      / \       | 
     i=1   i=2  i=3  i=2 i=3     i=3
     11    12   12   12  12      22                index = 1
    / \     |   
i=2  i=3    i=3
112  112    122                                    index = 2
 |
i=3
1122
*/
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> curr = new ArrayList<>();
        dfs(0, candidates, target, curr, result);
        return result;
    }
    public void dfs(int index, int[] candidates, int sum, List<Integer> curr, List<List<Integer>> result) {
        if(sum == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if(sum < 0 || index == candidates.length) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            curr.add(candidates[i]);
            dfs(i + 1, candidates, sum - candidates[i], curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}


