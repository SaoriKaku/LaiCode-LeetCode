
/*
Write a function to generate the generalized abbreviations of a word. 
Note: The order of the output does not matter.

Example:
Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
*/


// method 1: dfs
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    /*
                                          word
                             /                              \ 
    index0                 1                                  w 
                     /          \                      /            \
    index1         2              1o                 w1               wo
                 /   \          /    \             /    \           /     \
    index2     3      2r     1o1      1or        w2     w1r       wo1      wor 
              / \    / \     / \      /  \      / \     / \       / \      / \
    index3   4  3d 2r1 2rd 1o2 1o1d 1or1 1ord  w3 w2d w1r1 w1rd wo2 wo1d wor1 word
    */
    public List<String> generateAbbreviations(String word) {
        if(word == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        dfs(word, 0, 0, "", result);
        return result;
    }
    public void dfs(String word, int index, int count, String curr, List<String> result) {
        if(index == word.length()) {
            if(count > 0) {
                curr += count;
            }
            result.add(curr);
            return;
        }
        // not take charAt(index) into curr
        dfs(word, index + 1, count + 1, curr, result);
        // take charAt(index) into curr
        dfs(word, index + 1, 0, curr + (count > 0 ? count : "") + word.charAt(index), result);
    }
}

