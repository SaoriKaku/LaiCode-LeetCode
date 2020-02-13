
/*
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
We repeatedly make duplicate removals on S until we no longer can.
Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

Example 1:
Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 
Note:
1 <= S.length <= 20000
S consists only of English lowercase letters.
*/


// method 1: string
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(S.charAt(0));
        int i = 1;
        while(i < S.length()) {
            char curr = S.charAt(i);
            if(!stack.isEmpty() && curr == stack.peek()) {
                stack.pop();
            }
            else {
                stack.push(curr);
            }
            i++;
        }
        char[] result = new char[stack.size()];
        for(int j = result.length - 1; j >= 0; j--) {
            result[j] = stack.pop();
        }
        return String.valueOf(result);
    }
}


