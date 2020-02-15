
/*
Given a string S, return the number of substrings of length K with no repeated characters.

Example 1:
Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.

Example 2:
Input: S = "home", K = 5
Output: 0
Explanation: 
Notice K can be larger than the length of S. In this case is not possible to find any substring.
 
Note:
1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4
*/


// method 1: sliding window
// time complexity: O(n), space complexity: O(K)
class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
        int result = 0;
        int right = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] input = S.toCharArray();
        while(right < input.length) {
            // [left, right - 1]
            int length = right - left;
            if(length < K) {
                map.put(input[right], map.getOrDefault(input[right], 0) + 1);
                right++;
            }
            else {
                map.put(input[right], map.getOrDefault(input[right], 0) + 1);
                int count = map.get(input[left]);
                if(count == 1) {
                    map.remove(input[left]);
                }
                else {
                    map.put(input[left], count - 1);
                }
                right++;
                left++;
            }
            if(right - left == K && map.size() == K) {
                result++;
            }
        }
        return result;
    }
}

