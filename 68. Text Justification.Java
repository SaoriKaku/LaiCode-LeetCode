
/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:
A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.

Example 1:
Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:
Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.

Example 3:
Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/


// method 1: string
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while(index < words.length) {
            int beginIndex = index, currIndex = 0;
            boolean firstWord = true;
            while(index < words.length && currIndex <= maxWidth) {
                if(firstWord) {
                    firstWord = false; 
                }
                else {
                    currIndex += 1;
                }
                currIndex += words[index].length();
                index += 1;
            }
            StringBuilder line = new StringBuilder();
            if(currIndex <= maxWidth){ // last line
                line = lastLineOrSingleWord(words, maxWidth, beginIndex, words.length - 1);
            }
            else {
                index -= 1;
                int endIndex = index - 1;
                if(beginIndex == endIndex) {
                    line = lastLineOrSingleWord(words, maxWidth, beginIndex, beginIndex);
                }
                else {
                    currIndex = currIndex - words[index].length() - 1;
                    // spaceCount = the total number of space in a line
                    int spaceCount = maxWidth - currIndex + (endIndex - beginIndex);
                    // spaceValue = how many ' ' in the space
                    int spaceValue = spaceCount / (endIndex - beginIndex);
                    // oneMoreSpaceCount = how many words the spaceValue = spaceValue + 1
                    int oneMoreSpaceCount = spaceCount % (endIndex - beginIndex);
                    for(int i = beginIndex; i <= endIndex; i++) {
                        line.append(words[i]);
                        if(i == endIndex) break;
                        if(oneMoreSpaceCount > 0) {
                            for(int j = 0; j < spaceValue + 1; j++) {
                                line.append(" ");
                            }
                            oneMoreSpaceCount -= 1;
                        }
                        else {
                            for(int j = 0; j < spaceValue; j++) {
                                line.append(" ");
                            }
                        }
                    } //end for
                }// end else
            }
            result.add(String.valueOf(line));
        }
        return result;
    }
    public StringBuilder lastLineOrSingleWord(String[] words, int maxWidth, int beginIndex, int endIndex) {
        StringBuilder line = new StringBuilder();
        int lastLineIndex = 0;
        for(int i = beginIndex; i <= endIndex; i++) {
            line.append(words[i]);
            lastLineIndex += words[i].length();
            if(lastLineIndex < maxWidth) {
                line.append(" ");
                lastLineIndex += 1;
            }
        }
        while(lastLineIndex < maxWidth) {
            line.append(" ");
            lastLineIndex += 1;
        }
        return line;
    }
}

