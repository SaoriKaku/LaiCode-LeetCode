
/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/


// method 1: sliding window
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = 0;
        Set<Character> diff = new HashSet<>();
        // always move right, 
        // while map.contains currChar, move left
        while(right < s.length()) {
            char rightChar = s.charAt(right);
            if(diff.contains(rightChar)) {
                while(s.charAt(left) != rightChar) {
                    diff.remove(s.charAt(left));
                    left++;
                }
                // leftChar = rightChar, move left++
                left++;
            }
            diff.add(rightChar);
            result = Math.max(result, diff.size());
            right++;
        }
        return result;
    }
}




