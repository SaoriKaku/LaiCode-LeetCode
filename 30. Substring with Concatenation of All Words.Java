
/*
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:
Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
*/


// method 1: two pointers + HashMap
// s.length() = m, words.length = n, words[0].length = k
// time complexity: O(m * n), space complexity: O(k * n)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        int k = words[0].length(), n = words.length, index = 0;
        for(int i = 0; i <= s.length() - k * n; i++) {
            Map<String, Integer> map = new HashMap<>();
            for(String word: words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for(int j = i; j <= s.length() - k; j += k) {
                String curr = s.substring(j, j + k);
                if(!map.containsKey(curr)) {
                    break;
                }
                map.put(curr, map.get(curr) - 1);
                if(map.get(curr) == 0) {
                    map.remove(curr);
                }
                if(map.size() == 0) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }
}

