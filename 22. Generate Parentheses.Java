
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/


// method 1: DFS
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n <= 0) {
            return result;
        }
        StringBuilder curr = new StringBuilder();
        generateHelper(n, 0, 0, curr, result);
        return result;
    }
    public void generateHelper(int n, int left, int right, StringBuilder curr, List<String> result) {
        if(left + right == 2 * n) {
            result.add(String.valueOf(curr));
            return;
        }
        if(left < n) {
            curr.append("(");
            generateHelper(n, left + 1, right, curr, result);
            curr.deleteCharAt(curr.length() - 1);
        }
        if(left > right && right < n) {
            curr.append(")");
            generateHelper(n, left, right + 1, curr, result);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}




