
/*
Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
After doing so, return the final string.  If there is no way to do so, return the empty string.

Example 1:
Input: palindrome = "abccba"
Output: "aaccba"

Example 2:
Input: palindrome = "a"
Output: ""

Constraints:
1 <= palindrome.length <= 1000
palindrome consists of only lowercase English letters.
*/


// method 1: array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public String breakPalindrome(String palindrome) {
        if(palindrome == null || palindrome.length() <= 1) {
            return "";
        }
        char[] input = palindrome.toCharArray();
        int i = 0;
        for(i = 0; i < input.length; i++) {
            if(i != input.length / 2 && input[i] != 'a') {
                input[i] = 'a';
                break;
            }
        }
        if(i == input.length) {
            input[input.length - 1] = 'b';
        }
        return String.valueOf(input);
    }
}




