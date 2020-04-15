
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
Output: "wertf"

Example 2:
Input:
[
  "z",
  "x"
]
Output: "zx"

Example 3:
Input:
[
  "z",
  "x",
  "z"
] 
Output: "" 

Explanation: The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/


// method 1: topological sort
// time complexity: O(n * m), space complexity: O(n * m)
class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Integer> indegreeMap = new HashMap<>();
        Map<Character, Set<Character>> childrenMap = new HashMap<>();
        for(String word: words) {
            for(char curr: word.toCharArray()) {
                childrenMap.putIfAbsent(curr, new HashSet<>());
                indegreeMap.putIfAbsent(curr, 0);
            }
        }
        // childrenMap
        for(int i = 1; i < words.length; i++) {
            char[] word1 = words[i-1].toCharArray();
            char[] word2 = words[i].toCharArray();
            int index = 0;
            while(index < word1.length && index < word2.length && word1[index] == word2[index]) {
                index++;
            }
            if(index == word1.length || index == word2.length) {
                if(word1.length > word2.length) {
                    return "";
                }
                continue;
            }
            childrenMap.get(word1[index]).add(word2[index]);
        }
        // indegreeMap
        for(char parent: childrenMap.keySet()) {
            for(char children: childrenMap.get(parent)) {
                indegreeMap.put(children, indegreeMap.getOrDefault(children, 0) + 1);
            }
        }
        // System.out.println("childrenMap: " + childrenMap);
        // System.out.println("indegreeMap: " + indegreeMap);
        // queue
        Queue<Character> queue = new LinkedList<>();
        for(char curr: childrenMap.keySet()) {
            if(indegreeMap.get(curr) == 0) {
                queue.offer(curr);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!queue.isEmpty()) {
            char parent = queue.poll();
            result.append(parent);
            for(char neighbor: childrenMap.get(parent)) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                if(indegreeMap.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // System.out.println("result: " + String.valueOf(result));
        return result.length() == childrenMap.size() ? String.valueOf(result) : "";
    }
}




