
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


// method 1: BFS + DFS, BFS to build graph, DFS to get result from graph(endWord to beginWord), wordList size = n, maxLength word = m
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


/* data structure: set<Word>
    map<key: word, value: list of word's parents>
    map<key: word, value: word's level>
    bfs:
    1. push begin word into the queue
    2. for each word = queue.poll(), change each every character to check whether the new word in wordList
    if so, remove new word from wordList, add new word to queue
    3. record word's chidlren -> new word
    problem: for the first children, we remove new word from dict, add new word to queue
    when we generate the children again, we check a map and whether the new word is generated at the same layer
    dfs: 
    1. find all path from begin word to end word
    2. only return the shortest ways
*/
// method 2: bfs + dfs, bfs to build graph, dfs to get result from graph(beginWord to endWord)
// wordList.length = n, word.length = m, time complexity: O((m * 26)^n), space complexity: O(m * n)
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) {
            return new ArrayList<>();
        }
        Map<String, Integer> levelMap = new HashMap<>();
        Map<String, List<String>> childrenMap = new HashMap<>();
        if(bfs(beginWord, endWord, dict, levelMap, childrenMap)) {
            List<String> currList = new ArrayList<>();
            List<List<String>> result = new ArrayList<>();
            dfs(beginWord, endWord, childrenMap, currList, result);
            return result;
        }
        return new ArrayList<>();
    }
    public void dfs(String currWord, String endWord, Map<String, List<String>> childrenMap, List<String> currList, List<List<String>> result) {
        currList.add(currWord);
        if(currWord.equals(endWord)) {
            if(result.size() == 0 || result.get(0).size() > currList.size()) {
                result.clear();
                result.add(new ArrayList<>(currList));
            }
            else if(result.get(0).size() == currList.size()) {
                result.add(new ArrayList<>(currList));
            }
            currList.remove(currList.size() - 1);
            return;
        }
        if(childrenMap.containsKey(currWord)) {
            for(String nextWord: childrenMap.get(currWord)) {
                dfs(nextWord, endWord, childrenMap, currList, result);
            }
        }
        currList.remove(currList.size() - 1);
    }
    public boolean bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> levelMap, Map<String, List<String>> childrenMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dict.remove(beginWord);
        boolean found = false;
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level += 1;
            for(int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] currWordArray = currWord.toCharArray();
                for(int j = 0; j < currWordArray.length; j++) {
                    char currChar = currWordArray[j];
                    for(int k = 0; k < 26; k++) {
                        currWordArray[j] = (char)('a' + k);
                        String nextWord = String.valueOf(currWordArray);
                        if(nextWord.equals(endWord)) {
                            dict.remove(endWord);
                            levelMap.put(endWord, level);
                            childrenMap.putIfAbsent(currWord, new ArrayList<>());
                            childrenMap.get(currWord).add(endWord);
                            found = true;
                        }
                        else if(dict.contains(nextWord)) {
                            dict.remove(nextWord);
                            levelMap.put(nextWord, level);
                            childrenMap.putIfAbsent(currWord, new ArrayList<>());
                            childrenMap.get(currWord).add(nextWord);
                            queue.offer(nextWord);
                        }
                        else if(levelMap.containsKey(nextWord) && levelMap.get(nextWord) == level) {
                            childrenMap.putIfAbsent(currWord, new ArrayList<>());
                            childrenMap.get(currWord).add(nextWord);
                        }
                    }
                    currWordArray[j] = currChar;
                }
            }
        }
        return found;
    }
}



