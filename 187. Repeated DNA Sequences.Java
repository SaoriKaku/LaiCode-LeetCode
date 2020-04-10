
/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC", "CCCCCAAAAA"]
*/


// method 1: HashSet
// time complexity: O(n - l), space complexity: O(n - l)
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> visited = new HashSet<>();
        Set<String> result = new HashSet<>();
        for(int i = 0; i <= s.length() - 10; i++) {
            String curr = s.substring(i, i + 10);
            if(visited.contains(curr)) {
                result.add(curr);
            }
            else {
                visited.add(curr);
            }
        }
        return new ArrayList<String>(result);
    }
}


// method 2: Rabin-Karp
// time complexity: O(n - l), space complexity: O(n - l)
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s == null || s.length() <= 10) {
            return new ArrayList<String>();
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);
        Set<Integer> visited = new HashSet<>();
        Set<String> result = new HashSet<>();
        int curr = 0;
        for(int i = 0; i <= 9; i++) {
            curr = curr * 4 + map.get(s.charAt(i));
            visited.add(curr);
        }
        for(int i = 1; i <= s.length() - 10; i++) {
            // curr = curr * 4 - ... 前面 * 4，所以是[i-1] * 4^10
            curr = curr * 4 - map.get(s.charAt(i-1)) * (int)Math.pow(4, 10) + map.get(s.charAt(i + 9));
            if(visited.contains(curr)) {
                result.add(s.substring(i, i + 10));
            }
            else {
                visited.add(curr);
            }
        }
        return new ArrayList<String>(result);
    }
}

