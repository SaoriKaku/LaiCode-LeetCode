
/*
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:
Input: pattern = "abab", str = "redblueredblue"
Output: true

Example 2:
Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true

Example 3:
Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false

Notes:
You may assume both pattern and str contains only lowercase letters.
*/


// method 1: dfs + HashMap, m = pattern.length, n = str.length
// time complexity: O(n^m), space complexity: O(m)
class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        return dfs(pattern, 0, str, 0, map);
    }
    public boolean dfs(String pattern, int index1, String str, int index2, Map<Character, String> map) {
        if(index1 == pattern.length() && index2 == str.length()) {
            return true;
        }
        if(index1 == pattern.length() || index2 == str.length()) {
            return false;
        }
        for(int i = index2; i < str.length(); i++) {
            char currChar = pattern.charAt(index1);
            String currStr = str.substring(index2, i + 1);
            if(map.containsKey(currChar) && map.get(currChar).equals(currStr)) {
                if(dfs(pattern, index1 + 1, str, i + 1, map)) return true;
            }
            if(!map.containsKey(currChar) && !map.containsValue(currStr)) {
                map.put(currChar, currStr);
                if(dfs(pattern, index1 + 1, str, i + 1, map)) return true;
                map.remove(currChar);
            }
        }
        return false;
    }
}



