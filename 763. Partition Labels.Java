
/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
*/


// method 1: greedy + two pointers
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26];
        for(int i = 0; i < S.length(); i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        // anchor: begin, j: end
        int start = 0, end = 0;
        for(int i = 0; i < S.length(); i++) {
            end = Math.max(end, lastIndex[S.charAt(i) - 'a']);
            if(end == i) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }
}


