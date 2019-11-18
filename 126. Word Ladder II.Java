
/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/


/*
method 1: BFS + DFS, BFS to build graph, DFS to get result from graph
wordList: n, each word length: m
time complexity: 
BFS: O((26 * m)^n), branch factor: 26 * m; DFS: branch factor: n, tree height: n, O(n^n)
space complexity: O((n*m)^2)
*/
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(wordList == null || wordList.size() == 0 || beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0) {
            return result;
        }
        Set<String> dict = new HashSet<>();
        for(String word: wordList) {
            dict.add(word);
        }
        if(!dict.contains(endWord)) {
            return result;
        }
        // map<key = word, value = BFS level>
        Map<String, Integer> wordLevel = new HashMap<>();
        // map<key = word, value = a list of word's parents>
        Map<String, List<String>> wordBack = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int level = 1;
        boolean found = false;
        // BFS
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String parent = queue.poll();
                char[] currWord = parent.toCharArray();
                for(int j = 0; j < currWord.length; j++) {
                    char prev = currWord[j];
                    // if(parent.equals("lot")) {
                    //     System.out.println("test6: " + String.valueOf(currWord));
                    // }
                    for(int k = 0; k < 26; k++) {
                        currWord[j] = (char)(k + 'a');
                        String nextWord = String.valueOf(currWord);
                        // update wordLevel, when level == level
                        // update wordBack, add parent list
                        if(dict.contains(nextWord)) {
                            dict.remove(nextWord);
                            if(nextWord.equals(endWord)) {
                                found = true;
                            }
                            else {
                                queue.offer(nextWord);
                            }
                            wordLevel.put(nextWord, level);
                            List<String> parents = new ArrayList<>();
                            parents.add(parent);
                            wordBack.put(nextWord, parents);
                        }
                        else if(wordLevel.containsKey(nextWord) && wordLevel.get(nextWord) == level) {
                            List<String> parents = wordBack.get(nextWord);
                            parents.add(parent);
                            wordBack.put(nextWord, parents);
                        }
                         
                    } // for 26 letters
                    currWord[j] = prev;
                }// for each char
            }// for layer 
            level++;
        }
        
        // DFS
        if(found) {
            List<String> solu = new ArrayList<>();
            dfs(beginWord, endWord, wordBack, solu, result);
        }
        return result;
    }
    
    public void dfs(String beginWord, String curr, Map<String, List<String>> wordBack, List<String> solu, List<List<String>> result) {
        if(curr.equals(beginWord)) {
            solu.add(beginWord); // from endWord to beginWord
            Collections.reverse(solu);
            result.add(new ArrayList<>(solu));
            Collections.reverse(solu);
            solu.remove(beginWord);
            return;
        }
        List<String> parents = wordBack.get(curr);
        for(String parent: parents) {
            solu.add(curr);
            dfs(beginWord, parent, wordBack, solu, result);
            solu.remove(curr);
        }
    }
}

