
/*
Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.

Example 1:
Input: "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"

Example 2:
Input: "aeiou"
Output: ""
 
Note:
S consists of lowercase English letters only.
1 <= S.length <= 1000
*/


// method 1: string remove specific character
// time complexity: O(n), space complexity: O(1)
class Solution {
    public String removeVowels(String S) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        char[] input = S.toCharArray();
        int f = 0;
        int s = 0;
        while(f < input.length) {
            if(vowels.contains(input[f])) {
                f++;
            }
            else {
                input[s++] = input[f++];
            }
        }
        return String.valueOf(input).substring(0, s);
    }
}


