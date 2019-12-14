
/*
Given an input string, reverse the string word by word.

Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Note:
A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Follow up:
For C programmers, try to solve it in-place in O(1) extra space.
*/


// method 1: in place copy string
// time complexity: O(n), space complexity: O(1)
class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        char[] input = s.trim().toCharArray();
        int left = 0;
        int right = 0;
        int index = 0;
        while(right < input.length) {
            while(right < input.length && input[right] == ' ') {
                left++;
                right++;   
            }
            while(right < input.length && input[right] != ' ') {
                right++;
            }
            // word = [left, right), right == ' '
            reverseWord(input, left, right - 1);
            while(left < right) {
                input[index++] = input[left++];
            }
            // left == right
            if(right < input.length - 1) {
                input[index++] = ' ';
            }
        }
        //System.out.println("index = " + index);
        char[] result = Arrays.copyOfRange(input, 0, index);
        //System.out.println(String.valueOf(result));
        reverseWord(result, 0, result.length - 1);
        return String.valueOf(result);
    }
    public void reverseWord(char[] input, int left, int right) {
        while(left < right) {
            char temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }
    }
}

