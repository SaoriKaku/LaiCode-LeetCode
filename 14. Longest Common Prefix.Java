
/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.
*/


// method 1: vertical scan, compare for the same index for each word
// time complexity: O(m * n), space complexity: O(1)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        int charIndex = 0;
        StringBuilder result = new StringBuilder();
        while(true) {
            if(charIndex >= strs[0].length()) {
                return String.valueOf(result);
            }
            char currChar = strs[0].charAt(charIndex);
            for(int i = 1; i < strs.length; i++) {
                if(charIndex >= strs[i].length()) {
                    return String.valueOf(result);
                }
                if(strs[i].charAt(charIndex) != currChar) {
                    return String.valueOf(result);
                }
            }
            result.append(currChar);
            charIndex++;
        }
    }
}


