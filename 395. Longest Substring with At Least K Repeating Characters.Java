
/*
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/


// method 1: recursion + HashMap
// time complexity: O(n^2), space complexity: O(n^2)
class Solution {
    /*
    when you meet a character whose count < k, then 
    result = math(result, recursion(substring(left, right - 1)));
    */
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[26];
        for(char curr: s.toCharArray()) {
            map[curr - 'a']++; 
        }
        boolean lessThanK = false;
        for(int curr: map) {
            if(curr > 0 && curr < k) {
                lessThanK = true;
            }
        }
        if(!lessThanK) return s.length();
        int left = 0, right = 0, result = 0;
        while(right < s.length()) {
            if(map[s.charAt(right) - 'a'] < k) {
                result = Math.max(result, longestSubstring(s.substring(left, right), k));
                left = right + 1;
            }
            right++;
        }
        result = Math.max(result, longestSubstring(s.substring(left), k));
        return result;
    }
}




