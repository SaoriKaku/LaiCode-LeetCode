
/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/


// exceed time limit
class Solution {
    /*
    state: dp[i,j]: s.substring(from index = i, to index = j, including j) is valid
    base case: 
    1. dp[i][i] = false;
    2. dp[i][i+1]: if char[i]=(, char[i+1]=)
    induction rule: 
    1. if char[i]=(, char[j]=), dp[i][j] = dp[i+1][j-1]
    2. for(k = i+2; k < j; k++)
    dp[i][j] = true if dp[i][k-1] = true && dp[k][j] = true
    */
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLength = 0;
        for(int i = 0; i < n-1; i++) {
            if(s.charAt(i) == '(' && s.charAt(i+1) == ')') {
                dp[i][i+1] = true;
                maxLength = 2;
            }
        }
        //print2DArray(dp);
        for(int len = 4; len <= n; len += 2) {
            for(int i = 0; i + len - 1 < n; i++) {
                // [i, i + len - 1]
                int j = i + len - 1;
                if(s.charAt(i) == '(' && s.charAt(j) == ')') {
                    dp[i][j] = dp[i+1][j-1];
                }
                if(dp[i][j]) {
                    maxLength = Math.max(maxLength, len);
                    continue;
                }
                for(int k = i + 2; k < j; k += 2) {
                    if(dp[i][k-1] && dp[k][j]) {
                        //System.out.println("i = " + i + ", k = " + k);
                        dp[i][j] = true;
                        maxLength = Math.max(maxLength, len);
                        break;
                    }
                }// end k
            }// end i
        }// end len
        return maxLength;
    }
    public void print2DArray(boolean[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}


// method 1: stack
// time complexity: O(n), space complexity: O(n)
/* stack, beginIndex = -1, 
    if curr = '(', push into stack
    else curr = ')', 
        if stack.isEmpty, beginIndex = curr + 1, 
        stack.pop() = '('
        if stack.isEmpty, max = curr - beginIndex + 1,
        if !stack.isEmpty, max = curr - stack.peek()
            
    ")()())"
    index  begin
           Index  stack  max
    0      0      )      0
    1      1      )(
    2      1      )      2 - 1 + 1 = 2
    3      1      )(     2
    4      1      )      4 - 1 + 1 = 4   
    5      6      )      4
*/
class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int beginIndex = 0, max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(curr == '(') {
                stack.push(i);
            }
            else { // curr = ')'
                if(stack.isEmpty()) { // not a valid ()
                    beginIndex = i + 1;
                }
                else { // stack.peek() = '('
                    stack.pop();
                    if(stack.isEmpty()) {
                        max = Math.max(max, i - beginIndex + 1);
                    }
                    else {
                        max = Math.max(max, i - stack.peek());
                    }
                    //System.out.println("i = " + i + ", max = " + max);
                }
            }
        }
        return max;
    }
}


