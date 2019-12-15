
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
*/


// method 1: DP
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 && m == 0) {
            return 0;
        }
        if(m == 1 || n == 1) {
            return 1;
        }
        int[][] ways = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0) {
                    ways[i][j] = 1;
                }
                else {
                    ways[i][j] = ways[i-1][j] + ways[i][j-1];
                }
            }
        }
        return ways[m-1][n-1];
    }
}


// method 2: DP
// time complexity: O(m * n), space complexity: O(n)
class Solution {
    public int uniquePaths(int m, int n) {
        if(m == 0 && m == 0) {
            return 0;
        }
        if(m == 1 || n == 1) {
            return 1;
        }
        int[] before = new int[n];
        int[] after = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0) {
                    after[j] = 1;
                }
                else {
                    after[j] = after[j - 1] + before[j];
                }
            }
            //System.out.println("before: " + Arrays.toString(before));
            //System.out.println("after: " + Arrays.toString(after));
            before = after;
        }
        return after[n-1];
    }
}




