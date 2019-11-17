
/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/


// method 1: BFS
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        /*
        0 1 2 3 4 5 6 7
        ( ) ) ) ( ( ( )  
        i = 0, left = 1, right = 0
        i = 1, left = 0, right = 0
        i = 2, left = 0, right = 1
        i = 3, left = 0, right = 2
        i = 4, left = 1, right = 2
        i = 5, left = 2, right = 2
        i = 6, left = 3, right = 2
        i = 7, left = 2, right = 2
        */
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            result.add("");
            return result;
        }
        int left = 0;
        int right = 0;
        for(char curr: s.toCharArray()) {
            if(curr == '(') {
                left++;
            }
            else if(curr == ')') {
                if(left == 0) {
                    right++;
                }
                else {
                    left--;
                }
            }
        }
        //System.out.println("left = " + left + ", right = " + right);
        dfs(s, 0, left, right, result);
        if(result.size() == 0) result.add("");
        return result;
    }
    
    public void dfs(String input, int index, int left, int right, List<String> result) {
        //System.out.println("left = " + left + ", right = " + right + ", " + input + " " + check(input));
        if(left == 0 && right == 0 && check(input)) {
            result.add(input);
            return;
        }
        if(index == input.length()) {
            return;
        }
        // first delete ), when right == 0, then delete (
        for(int i = index; i < input.length(); i++) {
            char curr = input.charAt(i);
            if(i > 0 && curr == input.charAt(i - 1)) continue;
            else if(right > 0 && curr == ')') {
                dfs(input, i + 1, left, right, result);
                dfs(input.substring(0, i) + input.substring(i + 1), i, left, --right, result);
            }
            else if(right == 0 && left > 0 && curr == '(') {
                dfs(input, i + 1, left, right, result);
                dfs(input.substring(0, i) + input.substring(i + 1), i, --left, right, result);
            }
        }
    }
    
    public boolean check(String input) {
        // ( ) ) ) ( ( ( )
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if(curr == '(') {
                stack.push(curr);
            }
            else if(curr == ')') {
                if(!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
                else {
                    return false;
                } 
            }
        }
        return stack.isEmpty();
    }  
}


// method 2: DFS, simplify method check and add StringBuilder
// Runtime: 2 ms, faster than 76.23% of Java online submissions for Remove Invalid Parentheses.
// Memory Usage: 39.4 MB, less than 81.52% of Java online submissions for Remove Invalid Parentheses.
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        /*
        0 1 2 3 4 5 6 7
        ( ) ) ) ( ( ( )  
        i = 0, left = 1, right = 0
        i = 1, left = 0, right = 0
        i = 2, left = 0, right = 1
        i = 3, left = 0, right = 2
        i = 4, left = 1, right = 2
        i = 5, left = 2, right = 2
        i = 6, left = 3, right = 2
        i = 7, left = 2, right = 2
        */
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            result.add("");
            return result;
        }
        int left = 0;
        int right = 0;
        for(char curr: s.toCharArray()) {
            if(curr == '(') {
                left++;
            }
            else if(curr == ')') {
                if(left == 0) {
                    right++;
                }
                else {
                    left--;
                }
            }
        }
        //System.out.println("left = " + left + ", right = " + right);
        StringBuilder input = new StringBuilder(s);
        dfs(input, 0, left, right, result);
        if(result.size() == 0) result.add("");
        return result;
    }
    
    public void dfs(StringBuilder input, int index, int left, int right, List<String> result) {
        //System.out.println("left = " + left + ", right = " + right + ", " + input + " " + check(input));
        if(left == 0 && right == 0 && check(input)) {
            result.add(input.toString());
            return;
        }
        if(index == input.length()) {
            return;
        }
        // first delete ), when right == 0, then delete (
        for(int i = index; i < input.length(); i++) {
            char curr = input.charAt(i);
            if(i > 0 && curr == input.charAt(i - 1)) continue;
            else if(right > 0 && curr == ')') {
                StringBuilder prev = new StringBuilder(input);
                dfs(input.deleteCharAt(i), i, left, right - 1, result);
                input = prev;
            }
            else if(right == 0 && left > 0 && curr == '(') {
                StringBuilder prev = new StringBuilder(input);
                dfs(input.deleteCharAt(i), i, left - 1, right, result);
                input = prev;
            }
        }
    }
    
    public boolean check(StringBuilder input) {
        // ( ) ) ) ( ( ( )
        int count = 0;
        for(int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if(curr == '(') {
                count++;
            }
            else if(curr == ')') {
                count--;
                if(count < 0) return false;
            }
        }
        return count == 0;
    }  
}




