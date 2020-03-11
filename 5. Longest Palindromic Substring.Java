
/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/


// method 1: brute force
// time comleixty: O(n^3), space complexity: O(1)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int maxLength = 0;
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < s.length(); j++) {
                if(isPalindrome(s, i, j)) {
                    if(j - i > maxLength) {
                        maxLength = j - i;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength + 1);
    }
    
    public boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            }
            else {
                return false;
            }
        }
        return true;
    }
}


// method 2: DP
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        boolean[][] helper = new boolean[s.length()][s.length()];
        int start = 0;
        int maxLength = 0;
        for(int j = 0; j < s.length(); j++) {
            for(int i = 0; i <= j; i++) {
                // j - i = 2, 中间只隔着一个数字，不用判定
                // j - i = 1, ij挨着，不用判定
                // j = i，同一个字母，不用判定
                if(s.charAt(i) == s.charAt(j) && ((j - i <= 2) || helper[i + 1][j - 1])) {
                    helper[i][j] = true;
                    if(j - i > maxLength) {
                        maxLength = j - i;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength + 1);
    }
}


// method 3: DP
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        /*
            b a b a d
            0 1 2 3 4
        b 0 T F T F F
        a 1   T F T F
        b 2     T F F
        a 3       T F
        d 4         T
        dp[i, j] = true, if dp[i + 1, j - 1] == true and char[i-1] == char[j+1];
        */
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int end = 0;
        // i = distance, j = start index, i + j = end index
        for(int i = 0; i < n; i++) {
            for(int j = 0; i + j < n; j++) {
                if(i == 0) {
                    dp[j][i + j] = true;
                }
                else if(s.charAt(j) == s.charAt(i + j)) {
                    if(i == 1) {
                        dp[j][i + j] = true;
                    }
                    else {
                        dp[j][i + j] = dp[j + 1][i + j - 1];
                    }
                }
                else {
                    dp[j][i + j] = false;
                }
                // update result
                if(dp[j][i + j] && i > end - start) {
                    start = j;
                    end = i + j;
                }
            }
        }
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return s.substring(start, end + 1);
    }
}


// method 4: DP
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, length = 0;
        /*
        base case: dp[i][i] = T, dp[i][i + 1] = T
        induction rule: dp[i][j] = dp[i+1][j-1] if s[i] == s[j]
        */
        
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            start = i;
            length = 1;
        }
        for(int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                length = 2;
            }
        }
        for(int i = 2; i < n; i++) {
            for(int j = 0; i + j < n; j++) {
                // x - j = i, x = i + j
                if(s.charAt(i + j) == s.charAt(j) && dp[j + 1][i + j - 1] == true) {
                    dp[j][i + j] = true;
                    start = j;
                    length = i + 1;
                }
                else {
                    dp[j][i + j] = false;
                }
            }
        }
        //print2DArray(dp);
        //System.out.println("start = " + start + ", length = " + length);
        return s.substring(start, start + length);
    }
    public void print2DArray(boolean[][] input) {
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}



