
/*
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Note: The string size will be in the range [1, 100].
*/


// method 1: array + max, min count
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    if ( push into stack
    if * starCount++;
    if ) 1. if stack.isEmpty && rightCount == 0, return false
         2. if stack.peek() == (, stack.pop()
    return starCount >= rightCount && starCount >= stack.size();
    */
    public boolean checkValidString(String s) {
        if(s == null) return false;
        if(s.length() == 0) return true;
        int minRight = 0, maxRight = 0;
        for(char curr: s.toCharArray()) {
            if(curr == '(') {
                maxRight++;
                minRight++;
            }
            else if(curr == '*') {
                maxRight++; // when * == (
                // minRight--; // when * == )
                minRight = Math.max(minRight - 1, 0);
            }
            else {
                maxRight--;
                // minRight--;
                minRight = Math.max(minRight - 1, 0);
            }
            if(maxRight < 0) return false;
        }
        return minRight == 0;
    }
}




