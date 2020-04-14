
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.

Example:
Input:  n = 2
Output: ["11","69","88","96"]
*/


// method 1: dfs
// time complexity: O(5^n), space complexity: O(n)
class Solution {
    /*
    char[], length = n
    two pointer: i and j -> 11, 69, 88, 96
    if i == j, 1, 8
    */
    public List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<>();
        if(n <= 0) {
            return result;
        }
        char[] curr = new char[n];
        dfs(0, n-1, curr, result);
        return result;
    }
    public void dfs(int left, int right, char[] curr, List<String> result) {
        if(left == right) {
            curr[left] = '0';
            result.add(String.valueOf(curr));
            curr[left] = '1';
            result.add(String.valueOf(curr));
            curr[left] = '8';
            result.add(String.valueOf(curr));
            return;
        }
        if(left > right) {
            result.add(String.valueOf(curr));
            return;
        }
        if(left > 0) {
            curr[left] = '0';
            curr[right] = '0';
            dfs(left + 1, right - 1, curr, result);
        }
        curr[left] = '1';
        curr[right] = '1';
        dfs(left + 1, right - 1, curr, result);
        curr[left] = '8';
        curr[right] = '8';
        dfs(left + 1, right - 1, curr, result);
        curr[left] = '6';
        curr[right] = '9';
        dfs(left + 1, right - 1, curr, result);
        curr[left] = '9';
        curr[right] = '6';
        dfs(left + 1, right - 1, curr, result);
    } 
}



