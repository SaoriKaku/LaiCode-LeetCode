
/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/


// method 1: DFS, if n = input length, m = target, height of tree: n, n-nary: m
// time complexity: O(m^n), space complexity: O(n)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0) {
            return result;
        }
        //Arrays.sort(candidates);
        int[] count = new int[candidates.length];
        dfs(0, candidates, target, count, result);
        return result;
    }
    public void dfs(int index, int[] candidates, int sum, int[] count, List<List<Integer>> result) {
        if(sum == 0) {
            List<Integer> curr = new ArrayList<>();
            for(int k = 0; k < count.length; k++) {
                if(count[k] != 0) {
                    for(int j = 0; j < count[k]; j++) {
                        curr.add(candidates[k]);
                    }
                } 
            }
            result.add(curr);
            return; 
        }
        if(index == candidates.length) {
            return;
        }
        for(int i = 0; i <= sum/candidates[index]; i++) {
            int prev = count[index];
            count[index] = i;
            dfs(index + 1, candidates, sum - i * candidates[index], count, result);
            count[index] = prev;
        }
    }
}


// method 2: DFS, if n = input length, m = target, height of tree: h = target / (min in candidates), n-nary: n
// time complexity: O(n^h), space complexity: O(h)
/* [1, 2] 3
                      [ ]
                    /     \
               in=0i=0     in=0i=1   
                  1            2                  
                 /  \          |  
          in=0i=0    in=0i=1   in=1i=1
             11         12     22          
           /    \          
    in=0i=0   in=0i=1
    111         112
    [1, 2, 3] 4
                                                             [ ]
                                         /                    |            \
                                  in=0i=0                  in=0i=1          in=0i=2
                                     1                        2             3
                      /              |      \                / \            |
               in=0i=0            in=0i=1     in=0i=2  in=1i=1  in=1i=2     in=2i=2
                 11                  12         13         22   23          33
            /    |    \             /  \                        
      in=0i=0  in=0i=1  in=0i=2 in=1i=1 in=1i=2
        111       112     113     122   123               
   /     |     \
in=0i=0 in=0i=1 in=0i=2
1111    1112    1113
*/
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0) {
            return result;
        }
        //Arrays.sort(candidates);
        List<Integer> curr = new ArrayList<>();
        dfs(0, candidates, target, curr, result);
        return result;
    }
    public void dfs(int index, int[] candidates, int sum, List<Integer> curr, List<List<Integer>> result) {
        if(sum == 0) {
            result.add(new ArrayList<>(curr));
            return; 
        }
        if(sum < 0) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {
            curr.add(candidates[i]);
            dfs(i, candidates, sum - candidates[i], curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}

