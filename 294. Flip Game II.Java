
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:
Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/


// method 1: dfs
// time complexity: O(n^n), space complexity: O(n)
class Solution {
    public boolean canWin(String s) {
        char[] input = s.toCharArray();
        return helper(input);
    }
    public boolean helper(char[] input) {
        for(int i = 0; i < input.length - 1; i++) {
            if(input[i] == '+' && input[i+1] == '+') {
                input[i] = input[i+1] = '-';
                boolean result = !helper(input);
                input[i] = input[i+1] = '+';
                if(result) return true;
            }
        }
        return false;
    }
}




