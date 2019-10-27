
/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
*/


class Solution {
    public List<String> letterCombinations(String digits) {
        /* 23
                 2
               / | \ 
              a  b  c
            / | \
          ad bd fd
          each node has three child node
          the height of tree = length of input
        */
        char[][] map = new char[][] {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return result;
        }
        StringBuilder curr = new StringBuilder();
        dfs(map, digits, 0, curr, result);
        return result;
    }
    
    public void dfs(char[][] map, String digits, int index, StringBuilder curr, List<String> result) {
        if(index == digits.length()) {
            result.add(String.valueOf(curr));
            return;
        }
        int number = digits.charAt(index) - '0';
        for(int i = 0; i < map[number - 2].length; i++) {
            curr.append(map[number - 2][i]);
            dfs(map, digits, index + 1, curr, result);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}




