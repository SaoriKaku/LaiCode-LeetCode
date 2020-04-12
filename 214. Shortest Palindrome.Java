
/*
Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: "abcd"
Output: "dcbabcd"
*/


// method 1: brute force
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public String shortestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int left = 0, right = s.length() - 1, end = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }
            else {
                left = 0;
                end--;
                right = end;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i > end; i--) {
            sb.append(s.charAt(i));
        }
        return String.valueOf(sb) + s;
    }
}




