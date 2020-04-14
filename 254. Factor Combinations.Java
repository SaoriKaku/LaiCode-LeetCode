
/*
Numbers can be regarded as product of its factors. For example,
8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.

Example 1:
Input: 1
Output: []

Example 2:
Input: 37
Output:[]

Example 3:
Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

Example 4:
Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/


/*
    12
   /  \
2*6    3*4
  |    |
2*2*3  3*2*2
*/
// method 1: dfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if(n <= 3) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        dfs(2, n, curr, result);
        return result;
    }
    public void dfs(int start, int n, List<Integer> curr, List<List<Integer>> result) {
        if(n == 1) {
            if(curr.size() > 1) {
                result.add(new ArrayList<>(curr));
            }
            return;
        }
        for(int i = start; i <= n; i++) {
            if(n % i == 0) {
                curr.add(i);
                dfs(i, n / i, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }
}


