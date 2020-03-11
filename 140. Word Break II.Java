
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/


// method 1: dp + dfs
// time complexity: O(dp: s^2 + dfs: n^s), space complexity: O(s * n)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        int n = s.length();
        Set<String> dictSet = new HashSet<>();
        for(String curr: wordDict) {
            dictSet.add(curr);
        }
        // index is the last index of word in wordDict
        // map<key: index, value: list of index's parent>
        Map<Integer, List<Integer>> parentMap = new HashMap<>();
        for(int i = 0; i < n; i++) { // [0, i] in wordDict
            if(dictSet.contains(s.substring(0, i+1))) {
                List<Integer> parents = new ArrayList<>();
                parents.add(-1);
                parentMap.put(i, parents);
            }
            for(int j = 0; j <= i; j++) {
                if(parentMap.containsKey(j) && dictSet.contains(s.substring(j+1, i+1))) {
                    List<Integer> parents = parentMap.get(i);
                    if(parents == null) {
                        parents = new ArrayList<>();
                        parentMap.put(i, parents);
                    }
                    parentMap.get(i).add(j);
                }
            }
        }
        if(!parentMap.containsKey(n-1)) {
            return result;
        }
        List<String> curr = new ArrayList<>();
        dfs(s, n-1, parentMap, curr, result);
        return result;
    }
    public void dfs(String s, int index, Map<Integer, List<Integer>> parentMap, List<String> curr, List<String> result) {
        if(!parentMap.containsKey(index)) {
            return;
        }
        for(int parent: parentMap.get(index)) {
            if(parent == -1) {
                curr.add(s.substring(0, index + 1));
                StringBuilder solu = new StringBuilder();
                for(int i = curr.size() - 1; i > 0; i--) {
                    solu.append(curr.get(i));
                    solu.append(" ");
                }
                solu.append(curr.get(0));
                result.add(String.valueOf(solu));
                curr.remove(curr.size() - 1);
            }
            curr.add(s.substring(parent + 1, index + 1));
            dfs(s, parent, parentMap, curr, result);
            curr.remove(curr.size() - 1);
        }
    }
}


// method 1: dfs with memorization
// time complexity: O(s^n), space complexity: O((n*s)^2)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        Set<String> dictSet = new HashSet<>();
        for(String curr: wordDict) {
            dictSet.add(curr);
        }
        Map<String, List<String>> usedMap = new HashMap<>();
        result = dfs(s, dictSet, usedMap);
        return result;
    }
    public List<String> dfs(String s, Set<String> dictSet, Map<String, List<String>> usedMap) {
        if(usedMap.containsKey(s)) {
            return usedMap.get(s);
        }
        if(s.length() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            String subStr = s.substring(0, i+1);
            List<String> lastStrList = null;
            if(dictSet.contains(subStr)) {
                lastStrList = dfs(s.substring(i+1), dictSet, usedMap);
                if(lastStrList == null) {
                    result.add(subStr);
                }
                else {
                    for(String str: lastStrList) {
                        result.add(subStr + " " + str);
                    }
                }
            }
        }
        usedMap.put(s, result);
        return result;
    } 
}



