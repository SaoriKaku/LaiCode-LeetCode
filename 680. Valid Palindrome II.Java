
/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
*/


// Method 1: Brute Force time complexity: O(n^2), time exceed
// Method 2: Greedy time complexity: O(n), accpeted
class Solution {
    public boolean validPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        char[] input = s.toCharArray();
        return checkPalindrome(input, 0, input.length - 1, 0);
    }
    public boolean checkPalindrome(char[] input, int left, int right, int count) {
        while(left < right) {
            if(input[left] != input[right]) {
                if(count == 0) {
                   return checkPalindrome(input, left + 1, right, 1) || checkPalindrome(input, left, right - 1, 1);
                }
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

