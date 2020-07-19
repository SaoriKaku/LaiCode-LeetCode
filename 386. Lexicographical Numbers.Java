
/*
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
*/


// method 1: dfs, 
// time complexity: n = the number of digits, O(10^n), space complexity: O(n)
class Solution {
    public List<Integer> lexicalOrder(int n) {
        if(n <= 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= 9; i++) {
            dfs(n, i, result);
        }
        return result;
    }
    public void dfs(int n, int curr, List<Integer> result) {
        if(curr > n) {
            return;
        }
        result.add(curr);
        for(int i = 0; i <= 9; i++) {
            curr = 10 * curr + i;
            dfs(n, curr, result);
            curr = (curr - i) / 10;
        }
    }
}





