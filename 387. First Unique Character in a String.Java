
/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
*/


// method 1: only lowercase letters
// time complexity: O(n), space complexity: O(26)
class Solution {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        int[] dict = new int[26];
        for(char curr: s.toCharArray()) {
            int index = curr - 'a';
            dict[index]++;
        }
        // for(int i = 0; i < 26; i++) {
        //     System.out.print(dict[i] + " ");
        // }
        for(int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if(dict[index] == 1) {
                return i;
            }
        }
        return -1;
    }
}
