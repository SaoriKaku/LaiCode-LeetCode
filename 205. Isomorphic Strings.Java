
/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
*/

// method 1: use one map
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)) {
                if(map.get(a) != b) {
                    return false;
                }
            }
            else {
                if(map.containsValue(b)) {
                    return false;
                }
                map.put(a, b);
            }
        }
        return true;
    }
}


// method 2: use one map
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(mapS.containsKey(a)) {
                if(mapS.get(a) != b) {
                    return false;
                }
            }
            else {
                mapS.put(a, b);
            }
            if(mapT.containsKey(b)) {
                if(mapT.get(b) != a) {
                    return false;
                }
            }
            else {
                mapT.put(b, a);
            }
        }
        return true;
    }
}

