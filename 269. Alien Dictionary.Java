
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
        // Map<Character, Set<Character>>
        Map<Character, Set<Character>> successors = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            for(int j = 0; j < words[i].length(); j++) {
                char curr = words[i].charAt(j);
                if(!successors.containsKey(curr)) {
                    successors.put(curr, new HashSet<>());
                }
            }
        }
        for(int i = 1; i < words.length; i++) {
            char[] prevWord = words[i - 1].toCharArray();
            char[] currWord = words[i].toCharArray();
            int index = 0;
            while(index < prevWord.length && index < currWord.length && prevWord[index] == currWord[index]) {
                index++;
            }
            if(index == prevWord.length || index == currWord.length) {
                continue;
            }
            successors.get(prevWord[index]).add(currWord[index]);
        }
        //System.out.println("successors: " + successors);
        // Map<Character, Integer>
        Map<Character, Integer> indegree = new HashMap<>();
        for(char ancestor: successors.keySet()) {
            for(char successor: successors.get(ancestor)) {
                indegree.put(successor, indegree.getOrDefault(successor, 0) + 1);
            }
        }
        //System.out.println("indegree: " + indegree);
        Queue<Character> queue = new LinkedList<>();
        for(char curr: successors.keySet()) {
            if(!indegree.containsKey(curr)) {
                indegree.put(curr, 0);
                queue.offer(curr);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!queue.isEmpty()) {
            char currChar = queue.poll();
            result.append(currChar);
            for(char next: successors.get(currChar)) {
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return result.length() == successors.size() ? String.valueOf(result) : "";
    }
}

