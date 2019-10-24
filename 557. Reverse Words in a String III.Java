
/*
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
*/


class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        char[] input = s.toCharArray();
        int start = 0;
        int i = 0;
        for(i = 0; i < input.length; i++) {
            if(input[i] == ' ') {
                reverse(input, start, i - 1);
                start = i + 1;
            }
        }
        reverse(input, start, i - 1);
        return String.valueOf(input);
    }
    public void reverse(char[] input, int left, int right) {
        while(left < right) {
            char temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }
    }
}

