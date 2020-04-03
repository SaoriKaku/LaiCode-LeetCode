
/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

Example:
Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/


/* 
    a a b
    0 1 2
a 0 T T T
a 1   T F
b 2     T
    a a b
    0 1 2
a 0 1 1 2
a 1   1 0
b 2     1
*/
// method 1: DP(2D)
// time complexity: O(n * n * n), space complexity: O(n * n)
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = n;
            }
        }
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i + len <= n; i++) {
                // left: i, right = i + len - 1
                if(isPalindrome(s, i, i + len - 1)) {
                    // int right = i + len - 1;
                    // System.out.println("left = " + i + ", right = " + right);
                    dp[i][i + len - 1] = 0;
                    continue;
                }
                // [i, j] && [j + 1, i + len - 1]
                for(int j = i; j < i + len - 1; j++) {
                    if(dp[i][j] != n && dp[j + 1][i + len - 1] != n) {
                        dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], 
                                                      dp[i][j] + dp[j + 1][i + len - 1] + 1);
                    }
                }
            }
        }
        //print2DArray(dp, n);
        return dp[0][n-1];
    }
    public boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
    public void print2DArray(int[][] dp, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}


/* 
    helper[i][j]: whether[i, j] is palindrome
    if([i] == [j]) helper[i][j] = helper[i+1][j-1];
    dp[i]: whether [0, i] can be cut into palindrome
*/
// method 2: DP(2D)
// time complexity: O(n * n), space complexity: O(n * n)
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] helper = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            helper[i][i] = true;
        }
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i + len <= n; i++) {
                int right = i + len - 1;
                if(s.charAt(i) == s.charAt(right)) {
                    if(len == 2) {
                        helper[i][right] = true;
                    }
                    else {
                        helper[i][right] = helper[i+1][right-1];
                    }
                }
            }
        }
        //print2DArray(helper, n);
        int[] cut = new int[n];
        Arrays.fill(cut, n-1);
        for(int i = 0; i < n; i++) {
            if(helper[0][i]) {
                cut[i] = 0;
                continue;
            }
            for(int j = 0; j < i; j++) {
                // [0, j], [j+1][i]
                if(helper[j+1][i]) {
                    cut[i] = Math.min(cut[i], cut[j] + 1);
                }
            }
        }
        //System.out.println(Arrays.toString(cut));
        return cut[n-1];
    }
    public void print2DArray(boolean[][] helper, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(helper[i][j] + " ");
            }
            System.out.println();
        }
    }
}




