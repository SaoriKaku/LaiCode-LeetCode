
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

Example:
Input: s = "++++"
Output: 
[
  "--++",
  "+--+",
  "++--"
]

Note: If there is no valid move, return an empty list [].
*/


// method 1: array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return result;
        }
        char[] input = s.toCharArray();
        int n = s.length();
        for(int i = 0; i <= n - 2; i++) {
            if(input[i] == '+' && input[i+1] == '+') {
                input[i] = input[i+1] = '-';
                result.add(new String(input));
                input[i] = input[i+1] = '+';
            }
        }
        return result;
    }
}





