
/*
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":
Return true.

Example 2:
Given s = "apple", abbr = "a2e":
Return false.
*/


// method 1: String
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) {
            return false;
        }
        char[] input1 = word.toCharArray();
        char[] input2 = abbr.toCharArray();
        int i = 0, j = 0;
        while(i < input1.length && j < input2.length) {
            if(input1[i] == input2[j]) {
                i++;
                j++;
            } // the first digit should not be 0
            else if(input2[j] >= '1' && input2[j] <= '9') {
                int start = j;
                while(j < input2.length && Character.isDigit(input2[j])) {
                    j++;
                }
                int number = Integer.valueOf(abbr.substring(start, j));
                i += number;
            }
            else {
                return false;
            }
        }
        return i == input1.length && j == input2.length;
    }
}


// method 2: String
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) {
            return false;
        }
        int i = 0, j = 0;
        while(i < word.length() && j < abbr.length()) {
            if(word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
            } 
            // the first digit should not be 0
            else if(abbr.charAt(j) >= '1' && abbr.charAt(j) <= '9') {
                int start = j;
                while(j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    j++;
                }
                int number = Integer.valueOf(abbr.substring(start, j));
                i += number;
            }
            else {
                return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }
}


