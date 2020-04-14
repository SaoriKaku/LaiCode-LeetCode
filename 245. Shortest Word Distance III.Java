
/*
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3

Note:
You may assume word1 and word2 are both in the list.
*/


// method 1: Array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int result = words.length;
        if(word1.equals(word2)) {
            int prev = -1;
            for(int i = 0; i < words.length; i++) {
                if(words[i].equals(word1)) {
                    if(prev != -1) {
                        result = Math.min(result, i - prev);
                    }
                    prev = i;
                }
            }
        }
        else {
            int index1 = -1, index2 = -1;
            for(int i = 0; i < words.length; i++) {
                if(words[i].equals(word1)) {
                    index1 = i;
                }
                else if(words[i].equals(word2)) {
                    index2 = i;
                }
                if(index1 != -1 && index2 != -1) {
                    result = Math.min(result, Math.abs(index1 - index2));
                }
            }
        }
        return result;
    }
}




