
/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/


// method 1: sliding window, 
// idea: every step move right once, check result, while diff char > k, move left.
// time complexity: O(n), space complexity: O(k + 1)
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // when to move left: when diff char <= k
        // when to move right: when diff char < k
        if(s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int result = 0;
        Map<Character, Integer> diff = new HashMap<>();
        while(right < s.length()) {
            char currChar = s.charAt(right);
            diff.put(currChar, diff.getOrDefault(currChar, 0) + 1);
            
            if(diff.size() <= k) {
                result = Math.max(result, right - left + 1);
                //System.out.println("result = " + result + ", left = " + left + ", right = " + right);
            }
            while(diff.size() > k) {
                char leftChar = s.charAt(left);
                int count = diff.get(leftChar);
                if(count == 1) {
                    diff.remove(leftChar);
                }
                else {
                    diff.put(leftChar, diff.get(leftChar) - 1);
                }
                left++;
            }
            
             right++;
        }
        return result;
    }
}



