
/*
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/


// method 1: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int number = 0;
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(Character.isDigit(curr)) {
                number = 10 * number + curr - '0';
            }
            if(curr != ' ' && !Character.isDigit(curr) || i == s.length() - 1) {
                if(sign == '+') {
                    stack.push(number);
                }
                else if(sign == '-') {
                    stack.push(-number);
                }
                else if(sign == '*') {
                    stack.push(stack.pop() * number);
                }
                else if(sign == '/') {
                    stack.push(stack.pop() / number);
                }
                number = 0;
                sign = curr;
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}


// method 2: string
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int number = 0, prevNumber = 0, result = 0;
        char sign = '+';
        Set<Character> signs = new HashSet<>();
        signs.add('+');
        signs.add('-');
        signs.add('*');
        signs.add('/');
        for(int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isDigit(currChar)) {
                number = 10 * number + (int)(currChar - '0');
            }
            if(signs.contains(currChar) || i == s.length() - 1) {
                if(sign == '+') {
                    result += prevNumber;
                    prevNumber = number;
                }
                else if(sign == '-') {
                    result += prevNumber;
                    prevNumber = -1 * number;
                }
                else if(sign == '*') {
                    prevNumber = prevNumber * number;
                }
                else {
                    prevNumber = prevNumber / number;
                }
                sign = currChar;
                number = 0;
            }
            System.out.println("i = " + i + ", prev = " + prevNumber + ", number = " + number + ", result = " + result);
        }
        result += prevNumber;
        return result;
    }
}


