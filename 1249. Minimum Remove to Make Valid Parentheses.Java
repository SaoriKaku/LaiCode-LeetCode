
/*
Given a string s of '(' , ')' and lowercase English characters. 
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:
It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 
Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
 
Constraints:
1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
*/


// method 1: stack + HashSet
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String minRemoveToMakeValid(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(curr == '(') {
                stack.push(i);
            }
            else if(curr == ')') {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                }
                else {
                    set.add(i);
                }
            }
            // System.out.println("stack: " + stack);
            // System.out.println("set: " + set);
        }
        while(!stack.isEmpty()) {
            set.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return String.valueOf(sb);
    }
}



