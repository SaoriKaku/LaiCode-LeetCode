
/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:
Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.

Example 2:
Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
*/


// method 1: sliding window
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() <= 2) {
            return s.length();
        }
        char[] input = s.toCharArray();
        int left = 0;
        int right = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        // when to move right: set.size() <= 2
        // when to move left: set.size > 2
        while(left < input.length) {
            if(right < input.length && map.size() <= 2) {
                map.put(input[right], map.getOrDefault(input[right], 0) + 1);
                right++;
            }
            else {
                int count = map.get(input[left]);
                if(count == 1) {
                    map.remove(input[left]);
                }
                else {
                    map.put(input[left], count - 1);
                }
                left++;
            }
            if(map.size() <= 2) { // [left, right - 1]
                maxLength = Math.max(maxLength, right - left);
            }
        }
        return maxLength;
    }
}


// method 2: sliding window
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, result = 0;
        char[] input = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        while(left < s.length()) {
            while(right < s.length() && map.size() <= 2) {
                map.put(input[right], map.getOrDefault(input[right], 0) + 1);
                right++;
            }
            //System.out.println("left = " + left + ", right = " + right);
            if(map.size() == 3) { // size = 3, right, so right - 1 == (size = 3)
                result = Math.max(result, right - left - 1);
            }
            else {
                result = Math.max(result, right - left);
            }
            map.put(input[left], map.get(input[left]) - 1);
            if(map.get(input[left]) == 0) {
                map.remove(input[left]);
            }
            //System.out.println("set.size = " + set.size());
            left++;
        }
        return result;
    }
}



