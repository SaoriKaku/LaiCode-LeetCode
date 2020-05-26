
/*
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:
Input: "tree"
Output: "eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:
Input: "cccaaa"
Output: "cccaaa"
Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3: 
Input: "Aabb"
Output: "bbAa"
Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/


// method 1: HashMap + Sort
// time complexity: O(n + klogk), space complexity: O(n + k)
class Solution {
    public String frequencySort(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char curr: s.toCharArray()) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        List<Character> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (a, b) -> {
            return map.get(b) - map.get(a);
        });
        StringBuilder sb = new StringBuilder();
        for(char key: keys) {
            for(int i = 0; i < map.get(key); i++) {
                sb.append(key);
            }
        }
        return sb.toString();
    }
}


// method 2: bucket sort
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String frequencySort(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        int maxCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char curr: s.toCharArray()) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
            maxCount = Math.max(maxCount, map.get(curr));
        }
        List<List<Character>> buckets = new ArrayList<>();
        for(int i = 0; i <= maxCount; i++) {
            buckets.add(new ArrayList<>());
        }
        for(char key: map.keySet()) {
            int count = map.get(key);
            buckets.get(count).add(key);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = buckets.size() - 1; i >= 1; i--) {
            for(char curr: buckets.get(i)) {
                for(int j = 0; j < i; j++) {
                    sb.append(curr);
                }
            }
        }
        return sb.toString();
    }
}




