
/*
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
*/


// method 1: dfs + memorization, words.length == n, word.length == m
// time complexity: O(n * m^n), space complexity: O(m * n)
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if(words == null || words.length == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Set<String> dict = new HashSet<>();
        for(String word: words) {
            dict.add(word);
        }
        // key: word, value: how many words key is composed from dict
        Map<String, Boolean> visited = new HashMap<>();
        for(String word: words) {
            //System.out.println("word = " + word);
            if(dfs(word, dict, visited)) {
                result.add(word);
            }
        }
        return result;
    }
    public boolean dfs(String word, Set<String> dict, Map<String, Boolean> visited) {
        if(visited.containsKey(word)) {
            return visited.get(word);
        }
        for(int i = 0; i < word.length() - 1; i++) {
            if(dict.contains(word.substring(0, i + 1))) {
                String suffix = word.substring(i + 1);
                if(dict.contains(suffix) || dfs(suffix, dict, visited)) {
                    visited.put(word, true);
                    return true;
                }
            }
        }
        visited.put(word, false);
        return false;
    }
}





