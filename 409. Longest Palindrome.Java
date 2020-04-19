
/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input:
"abccccdd"
Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int longestPalindrome(String s) {
        int count = 0;
        int[] map = new int[128];
        for(char curr: s.toCharArray()) {
            map[curr]++;
            if(map[curr] % 2 == 0) {
                count--;
            }
            else {
                count++;
            }
        }
        return Math.min(s.length(), s.length() - count + 1); 
    }
}


