
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


// method 2: optimized method 1
// time complexity: O(n), space complexity: O(26) = O(1)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int[] map = new int[26];
        int k = s1.length(), n = s2.length(), count = 0;
        for(int i = 0; i < k; i++) {
            map[s1.charAt(i) - 'a']++;
        }
        for(int i = 0; i < k; i++) {
            int index = s2.charAt(i) - 'a';
            if(map[index] > 0) {
                count++;
            }
            map[index]--;
        }
        if(count == k) {
            return true;
        }
        for(int i = k; i < n; i++) {
            int left = s2.charAt(i - k) - 'a';
            int right = s2.charAt(i) - 'a';
            if(map[left] > -1) { // left > 0, left - 1 > -1
                count--;
            }
            map[left]++;
            if(map[right] > 0) {
                count++;
            }
            map[right]--;
            if(count == k) {
                return true;
            }
        }
        return false;
    }
}



