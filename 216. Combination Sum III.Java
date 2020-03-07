
/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/


// method 1: dfs
// time complexity: O(9^k), space complexity: O(k)
class Solution {
    /*          0
        /       |     |    \
       1        2     3     4...
     / | \     / | \
    2  3  4...3  4  5...
    */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if(k <= 0 || n <= 0) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        dfs(k, n, 1, curr, result);
        return result;
    }
    public void dfs(int k, int sum, int index, List<Integer> curr,  List<List<Integer>> result) {
        if(sum < 0) {
            return;
        }
        if(curr.size() == k) {
            if(sum == 0) {
                result.add(new ArrayList<>(curr));
                return;
            }
        }
        for(int i = index; i <= 9; i++) {
            curr.add(i);
            dfs(k, sum - i, i + 1, curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}

