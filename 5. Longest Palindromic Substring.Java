
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
