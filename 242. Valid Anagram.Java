
/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
*/


// time complexity: O(m + n), space complexity: O(n)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(char curr: s.toCharArray()) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        for(char curr: t.toCharArray()) {
            Integer count = map.get(curr);
            if(count == null) {
                return false;
            }
            if(count == 1) {
                map.remove(curr);
            }
            else {
                map.put(curr, count - 1);
            }
        }
        return map.size() == 0;
    }
}




