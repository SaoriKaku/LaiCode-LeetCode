
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


// method 1: bfs + dfs, wordList size = n, maxLength word = m
// time complexity: O(bfs: (m * 26)^n + dfs: n^n), space complexity: O(nm ^2)
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if(beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return result;
        }
        Set<String> dictSet = new HashSet<>();
        for(String curr: wordList) {
            dictSet.add(curr);
        }
        if(!dictSet.contains(endWord)) {
            return result;
        }// parent<key: word, value: list of word's parent>
        Map<String, List<String>> parentMap = new HashMap<>();
        // level<key: word, value: word's level>
        Map<String, Integer> levelMap = new HashMap<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int level = 0;
        boolean found = false;
        // BFS
        while(!queue.isEmpty()) {
            if(found) {
                break;
            }
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++) {
                String currWordStr = queue.poll();
                char[] currWord = currWordStr.toCharArray();
                for(int j = 0; j < currWord.length; j++) {
                    char currChar = currWord[j];
                    for(char k = 'a'; k <= 'z'; k++) {
                        currWord[j] = k;
                        String nextWord = String.valueOf(currWord);
                        if(dictSet.contains(nextWord)) {
                            dictSet.remove(nextWord);
                            if(nextWord.equals(endWord)) {
                                found = true;
                            }
                            else {
                                queue.offer(nextWord);
                            }
                            List<String> parents = new ArrayList<>();
                            parents.add(currWordStr);
                            parentMap.put(nextWord, parents);
                            levelMap.put(nextWord, level);
                        }
                        // one the same level, dict contains nextWord, already add nextWord to parentMap, add nextWord's parent to parentMap
                        // 在同层，nextWord不在dict里面，如果是之前的同层词添加的，就添加nextWord的parent；否则不同层就不用考虑
                        else if(levelMap.containsKey(nextWord) && levelMap.get(nextWord) == level) {
                            parentMap.get(nextWord).add(currWordStr);
                        }
                    } // end k
                    currWord[j] = currChar;
                }// end j
            }// end i 
        }// end while
        if(found) {
            List<String> currPath = new ArrayList<>();
            currPath.add(endWord);
            dfs(beginWord, endWord, parentMap, currPath, result);
        }
        return result;
    }
    public void dfs(String beginWord, String currWord, Map<String, List<String>> parentMap, List<String> currPath, List<List<String>> result) {
        if(currWord.equals(beginWord)) {
            Collections.reverse(currPath);
            result.add(new ArrayList<>(currPath));
            Collections.reverse(currPath);
            return;
        }
        List<String> parents = parentMap.get(currWord);
        for(String parent: parents) {
            currPath.add(parent);
            dfs(beginWord, parent, parentMap, currPath, result);
            currPath.remove(currPath.size() - 1);
        }
    }
}

