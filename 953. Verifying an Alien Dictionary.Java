
/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 
Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/


// method 1: HashMap + Sort
// Assume words.length = m, word.length = n; time complexity: O(m * n), space complexity: O(26)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if(words.length == 1) {
            return true;
        }
        int[] map = new int[26];
        for(int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for(int i = 0; i < words.length - 1; i++) {
            if(!firstBeforeSecond(words[i], words[i + 1], map)) {
                return false;
            }
        }
        return true;
    }
    public boolean firstBeforeSecond(String first, String second, int[] map) {
        int i = 0;
        while(i < first.length() && i < second.length()) {
            char a = first.charAt(i);
            char b = second.charAt(i);
            if(map[a - 'a'] > map[b - 'a']) {
                //System.out.println("a = " + a + ", b = " + b);
                return false;
            }
            else if(map[a - 'a'] < map[b - 'a']) {
                return true;
            }
            else {
                i++;
            }
        }
        return first.length() <= second.length();
    }
}



