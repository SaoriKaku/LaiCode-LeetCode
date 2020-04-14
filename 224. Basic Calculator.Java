
/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:
Input: "1 + 1"
Output: 2

Example 2:
Input: " 2-1 + 2 "
Output: 3

Example 3:
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/


// method 1: stack
// time complexity: O(n), space complexity: O(n)
/* result: the result value in the curr (), 
when meet next (, push result and sign into stack
when meet ), result = curr result * sign + result in stack(prev result)
*/
class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int result = 0, sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(Character.isDigit(curr)) {
                int number = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    number = 10 * number + s.charAt(i) - '0';
                    i++;
                }
                i--; // not skip ')'
                result += sign * number;
            }
            else if(curr == '+') {
                sign = 1;
            }
            else if(curr == '-') {
                sign = -1;
            }
            else if(curr == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            }
            else if(curr == ')') {
                result = result * stack.pop() + stack.pop();
            }
            //System.out.println("result = " + result + ", stack: " + stack);
        }
        return result;
    }
}



