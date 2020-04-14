
/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/


// method 1: HashMap, n = array.length, m = array[i].length
// time complexity: O(m * n + n), space complexity: O(n)
class Solution {
    /*
    'b'-'a' = 1, 'c'-'b' = 2, "abc" = "12"
    "ba", if 'a'<'b', 'a'-'b'+ 26
    */
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String currStr: strings) {
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < currStr.length(); i++) {
                char currChar = currStr.charAt(i);
                char prevChar = currStr.charAt(i-1);
                int index = currChar >= prevChar ? currChar - prevChar : currChar - prevChar + 26;
                sb.append(index);
            }
            String key = new String(sb);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(currStr);
        }
        for(String key: map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }
}


