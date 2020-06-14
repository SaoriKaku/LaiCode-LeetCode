
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/


// method 1: DP
// time complexity: O(n^3), space complexity: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        HashSet<String> dict = new HashSet<>();
        for(String word: wordDict) {
            dict.add(word);
        }
        boolean[] inDict = new boolean[s.length() + 1];
        // inDict[i]: whether s[0, i) is in dict
        for(int i = 1; i <= s.length(); i++) { // !!! 这里的<=非常重要
            if(dict.contains(s.substring(0, i))) {
                inDict[i] = true;
                continue;
            }
            for(int j = 0; j < i; j++) {
                if(inDict[j] && dict.contains(s.substring(j, i))) {
                    inDict[i] = true;
                    break;
                }
            }
        }
        return inDict[s.length()];
    }
}


// method 2: DP(1D)
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                // [0, j-1] + [j, i-1]
                if(dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}


// method 3: dfs + memorization
// time complexity: O(n^2), sapce complexity: O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        Map<String, Boolean> cache = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, cache, dict);
    }
    public boolean dfs(String s, Map<String, Boolean> cache, Set<String> dict) {
        if(cache.containsKey(s)) {
            return cache.get(s);
        }
        if(dict.contains(s)) {
            return true;
        }
        for(int i = 1; i < s.length(); i++) {
            String subStr1 = s.substring(0, i);
            String subStr2 = s.substring(i);
            if(dict.contains(subStr1) && dfs(subStr2, cache, dict)) {
                cache.put(s, true);
                return true;
            }
        }
        cache.put(s, false);
        return false;
    }
}




