
/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"
Output:
[0, 1, 2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/


// method 1: sliding window = p.length
// time compleixty: O(s.length); space complexity: O(26)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || p == null || p.length() == 0) {
            return result;
        }
        int[] map = new int[26];
        for(char curr: p.toCharArray()) {
            map[curr - 'a']++;
        }
        int count = 0;
        for(int curr: map) {
            if(curr > 0) {
                count++;
            }
        }
        int left = 0;
        int right = 0;
        while(right < s.length()) {
            //System.out.println("left = " + left + ", right = " + right);
            // a b a a c b a b c
            // l   r
            char rightChar = s.charAt(right);
            char leftChar = s.charAt(left);
            if(right - left < p.length() - 1) {
                map[rightChar - 'a']--;
                if(map[rightChar - 'a'] == 0) {
                    count--;
                }
                right++;
            }
            else {
                // at this time right - left = p.length - 1
                map[rightChar - 'a']--;
                if(map[rightChar - 'a'] == 0) {
                    count--;
                }
                if(count == 0) {
                    result.add(left);
                }
                // after check when right - left = p.length - 1, 
                // we move left++
                map[leftChar - 'a']++;
                if(map[leftChar - 'a'] == 1) {
                    count++;
                }
                right++;
                left++;
            }
        }
        return result;
    }
}


// method 2: optimized method 1
// time compleixty: O(s.length); space complexity: O(26)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null || p.length() > s.length()) {
            return result;
        }
        int[] map = new int[26];
        int k = p.length(), n = s.length(), count = 0;
        for(int i = 0; i < k; i++) {
            map[p.charAt(i) - 'a']++;
        }
        for(int i = 0; i < k; i++) {
            int index = s.charAt(i) - 'a';
            if(map[index] > 0) {
                count++;
            }
            map[index]--;
        }
        if(count == k) {
            result.add(0);
        }
        for(int i = k; i < n; i++) {
            int left = s.charAt(i - k) - 'a';
            int right = s.charAt(i) - 'a';
            if(map[left] > -1) {
                count--;
            }
            map[left]++;
            if(map[right] > 0) {
                count++;
            }
            map[right]--;
            if(count == k) {
                result.add(i - k + 1);
            }
        }
        return result;
    }
}




