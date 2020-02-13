
/*
Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made.
It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 
Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/


// method 1: string stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    class Pair {
        char letter;
        int count;
        public Pair(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }
    }
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();
        for(char curr: s.toCharArray()) {
            if(!stack.isEmpty() && curr == stack.peek().letter) {
                int count = stack.peek().count;
                count++;
                if(count == k) {
                    stack.pop();
                }
                else {
                    stack.peek().count = count;
                }  
            }
            else {
                stack.push(new Pair(curr, 1));
            }
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            Pair pair = stack.pop();
            char curr = pair.letter;
            int count = pair.count;
            for(int i = 0; i < count; i++) {
                result.append(curr);
            }
        }
        return result.reverse().toString();
    }
}



