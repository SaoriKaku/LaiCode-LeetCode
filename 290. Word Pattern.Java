
/*
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:
Input: pattern = "abba", str = "dog cat cat dog"
Output: true

Example 2:
Input:pattern = "abba", str = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", str = "dog dog dog dog"
Output: false

Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null) {
            return false;
        }
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) return false;
        for(int i = 0; i < pattern.length(); i++) {
            char currChar = pattern.charAt(i);
            String currStr = strs[i];
            if(map1.containsKey(currChar)) {
                if(!map1.get(currChar).equals(currStr)) return false;
            }
            else {
                map1.put(currChar, currStr);
            }
            if(map2.containsKey(currStr)) {
                if(map2.get(currStr) != currChar) return false;
            }
            else {
                map2.put(currStr, currChar);
            }
        }
        return true;
    }
}



