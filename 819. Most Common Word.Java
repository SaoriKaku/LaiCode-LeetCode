
/*
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 
Note:
1 <= paragraph.length <= 1000.
0 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
*/


// method 1: HashMap + HashSet
// time complexity: O(m + n), space complexity: O(m + n)
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        /*
        1. put each word in banned into a set
        2. traverse string, find each word, build map, 
        if(word in set) continue;
        else put word in map, update max and corresponding string
        return this corresponding string
        */
        Set<String> set = new HashSet<>();
        for(String word: banned) {
            set.add(word);
        }
        int i = 0, n = paragraph.length(), max = 0;
        String result = "";
        Map<String, Integer> map = new HashMap<>();
        while(i < n) {
            if(Character.isLetter(paragraph.charAt(i))) {
                StringBuilder word = new StringBuilder();
                while(i < n && Character.isLetter(paragraph.charAt(i))) {
                    word.append(paragraph.charAt(i));
                    i++;
                }
                String curr = String.valueOf(word).toLowerCase();
                if(set.contains(curr)) continue;
                map.put(curr, map.getOrDefault(curr, 0) + 1);
                if(map.get(curr) > max) {
                    max = map.get(curr);
                    result = curr;
                }
            }
            i++;
        }
        return result;
    }
}



