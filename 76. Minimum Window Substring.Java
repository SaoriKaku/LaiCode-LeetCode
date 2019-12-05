
/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/


// method 1: sliding window, two pointers
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    int min = Integer.MAX_VALUE
    int charLeft = map.length();
    HashMap<Character, Integer> map = <key = char, value = number of key>
    left: if count == 0
    right: move one step right
    */
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(char curr: t.toCharArray()) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        int charLeft = map.size();
        int minLength = Integer.MAX_VALUE;
        int startIndex = 0;
        int left = 0;
        int right = 0;
        while(true) {
            // when to move left: when [left, rignt) contains map
            if(charLeft == 0) {
                char leftChar = s.charAt(left);
                if(map.containsKey(leftChar)) {
                    int count = map.get(leftChar);
                    map.put(leftChar, count + 1);
                    if(count == 0) {
                        charLeft++;
                    }
                }
                left++;
            }
            // else move right
            else {
                if(right == s.length()) {
                    break;
                }
                // so right < s.length()
                char rightChar = s.charAt(right);
                if(map.containsKey(rightChar)) {
                    int count = map.get(rightChar);
                    map.put(rightChar, count - 1);
                    if(count == 1) {
                        charLeft--;
                    }
                }
                right++;
            }
            // update minLength and startIndex, [left, right)
            if(charLeft == 0) {
                if(minLength > right - left) {
                    minLength = right - left;
                    startIndex = left;
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLength);
    }
}

