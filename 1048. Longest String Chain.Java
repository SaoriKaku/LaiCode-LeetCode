
/*
Given a list of words, each word consists of English lowercase letters.
Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
Return the longest possible length of a word chain with words chosen from the given list of words.

Example 1:
Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".
 
Note:
1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
*/


// method 1: from small to big, a -> ab, dfs + memorization
// n = words.length, m = word.length(), time complexity: O(n * m), space complexity: O(n)
class Solution {
    public int longestStrChain(String[] words) {
        if(words == null || words.length == 0) {
            return 0;
        }
        Set<String> dict = new HashSet<>();
        for(String word: words) {
            dict.add(word);
        }
        Map<String, Integer> cache = new HashMap<>();
        int maxLength = 0;
        for(String word: words) {
            if(!cache.containsKey(word)) {
                maxLength = Math.max(maxLength, dfs(word, dict, cache));
            }
        }
        return maxLength;
    }
    public int dfs(String word, Set<String> dict, Map<String, Integer> cache) {
        if(cache.containsKey(word)) {
            return cache.get(word);
        }
        int result = 1;
        dict.remove(word);
        for(int i = 0; i <= word.length(); i++) {
            for(int j = 0; j <= 25; j++) {
                char nextChar = (char)('a' + j);
                String nextWord = word.substring(0, i) + nextChar + word.substring(i);
                if(dict.contains(nextWord)) {
                    result = Math.max(result, dfs(nextWord, dict, cache) + 1);
                }
            }
        }
        dict.add(word);
        cache.put(word, result);
        return result;
    }
}


// method 2: from big to small, ab -> a, dfs + memorization
// n = words.length, m = word.length(), time complexity: O(n * m), space complexity: O(n)
class Solution {
    public int longestStrChain(String[] words) {
        if(words == null || words.length == 0) {
            return 0;
        }
        Set<String> dict = new HashSet<>();
        for(String word: words) {
            dict.add(word);
        }
        Map<String, Integer> cache = new HashMap<>();
        int maxLength = 0;
        for(String word: words) {
            if(!cache.containsKey(word)) {
                maxLength = Math.max(maxLength, dfs(word, dict, cache));
            }
        }
        return maxLength;
    }
    public int dfs(String word, Set<String> dict, Map<String, Integer> cache) {
        if(cache.containsKey(word)) {
            return cache.get(word);
        }
        int result = 1;
        StringBuilder sb = new StringBuilder(word);
        for(int i = 0; i < word.length(); i++) {
            sb.deleteCharAt(i);
            String nextWord = sb.toString();
            if(dict.contains(nextWord)) {
                result = Math.max(result, dfs(nextWord, dict, cache) + 1);
            }
            sb.insert(i, word.charAt(i));
        }
        cache.put(word, result);
        return result;
    }
}





