
/*
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
Formally, a parentheses string is valid if and only if:
It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

Example 1:
Input: "())"
Output: 1

Example 2:
Input: "((("
Output: 3

Example 3:
Input: "()"
Output: 0

Example 4:
Input: "()))(("
Output: 4
 
Note:
S.length <= 1000
S only consists of '(' and ')' characters.
*/


// method 1: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int minAddToMakeValid(String S) {
        if(S == null || S.length() == 0) {
            return 0;
        }
        Deque<Character> stack = new ArrayDeque<>();
        int result = 0;
        for(int i = 0; i < S.length(); i++) {
            char currChar = S.charAt(i);
            if(currChar == '(') {
                stack.push('(');
            }
            else if(currChar == ')') {
                if(stack.isEmpty()) {
                    result += 1;
                }
                else {
                    stack.pop();
                }
            }
        }
        return result + stack.size();
    }
}


// method 2: two integer
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int minAddToMakeValid(String S) {
        if(S == null || S.length() == 0) {
            return 0;
        }
        int leftCount = 0, result = 0;
        for(int i = 0; i < S.length(); i++) {
            char currChar = S.charAt(i);
            if(currChar == '(') {
                leftCount++;
            }
            else if(currChar == ')') {
                if(leftCount > 0) {
                    leftCount--;
                }
                else {
                    result++;
                }
            }
        }
        return result + leftCount;
    }
}





