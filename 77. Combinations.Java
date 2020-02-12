
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/


// method 1: dfs
// time complexity: O((n + 1 - k) ^ k), space complexity: O(k)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if(n == 0 || k == 0) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        combineHelper(n, k, 1, curr, result);
        return result;
    }
    /*      root
       /     |   \
      1      2    3
    / | \   / \   |
   2  3  4 3   4  4
  
    */
    private void combineHelper(int n, int k, int layer, List<Integer> curr, List<List<Integer>> result) {
        if(curr.size() == k) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for(int i = layer; i <= n; i++) {
            // System.out.println("layer = " + layer + ", i = " + i);
            curr.add(i);
            combineHelper(n, k, i + 1, curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}




