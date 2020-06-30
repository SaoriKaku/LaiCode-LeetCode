
/*
Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/


// method 1: recursive
// time complexity: O(n), space complexity: O(k)
class Solution {
    public String decodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int[] index = new int[1];
        return dfs(s, index);
    }
    public String dfs(String s, int[] index) {
        String str = "";
        while(index[0] < s.length()) {
            while(index[0] < s.length() && Character.isLetter(s.charAt(index[0]))) {
                str += s.charAt(index[0]);
                index[0]++;
            }
            int count = 0;
            while(index[0] < s.length() && Character.isDigit(s.charAt(index[0]))) {
                count = 10 * count + (int)(s.charAt(index[0]) - '0');
                index[0]++;
            }
            if(index[0] < s.length() && s.charAt(index[0]) == '[') {
                index[0]++;
                String currStr = dfs(s, index);
                for(int i = 0; i < count; i++) {
                    str += currStr;
                }
            }
            if(index[0] < s.length() && s.charAt(index[0]) == ']') {
                index[0]++;
                return str;
            }
        }
        return str;
    }
}


// method 2: iterative
// time complexity: O(n), space complexity: O(k)
class Solution {
    public String decodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<String> strStack = new ArrayDeque<>();
        String str = "";
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(Character.isLetter(curr)) {
                str += curr; 
            }
            else if(Character.isDigit(curr)) {
                count = 10 * count + (int)(curr - '0');
            }
            else if(curr == '[') {
                intStack.push(count);
                strStack.push(str);
                count = 0;
                str = "";
            }
            else if(curr == ']') {
                int time = intStack.pop();
                String temp = strStack.pop();
                for(int j = 0; j < time; j++) {
                    temp += str;
                }
                str = temp;
            }
        }
        return str;
    }
}



