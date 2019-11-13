
/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 
Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
*/


// method 1: sliding window, use array[26] instead of map
// time complexity: O(n), space complexity: O(26)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0|| s1.length() > s2.length()) {
            return false;
        }
        int[] letter = new int[26];
        // count how many different letters in s1
        int count = 0;
        for(char curr: s1.toCharArray()) {
            int index = curr - 'a';
            if(letter[index] == 0) {
                count++;
            }
            letter[index]++;
        }
        int left = 0;
        int right = 0;
        while(right < s2.length()) {
            if(right - left == s1.length()) {
                char currLeft = s2.charAt(left);
                int indexLeft = currLeft - 'a';
                letter[indexLeft]++;
                if(letter[indexLeft] == 1) {
                    count++;
                }
                left++;
            }
            char currRight = s2.charAt(right);
            int indexRight = currRight - 'a';
            letter[indexRight]--;
            if(letter[indexRight] == 0) {
                count--;
                if(count == 0) {
                    return true;
                }
            }
            right++;
        }
        return false;
    }
}
