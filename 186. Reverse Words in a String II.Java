
/*
Given an input string , reverse the string word by word. 

Example:
Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

Note: 
A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
*/


// method 1: string
// time complexity: O(n), space complexity: O(1)
class Solution {
    public void reverseWords(char[] s) {
        int i = 0;
        while(i < s.length) {
            while(i < s.length && s[i] == ' ') {
                i++;
            }
            int temp = i;
            while(i < s.length && s[i] != ' ') {
                i++;
            }
            reverse(s, temp, i - 1);
        }
        reverse(s, 0, s.length - 1);
    }
    private void reverse(char[] array, int left, int right) {
        while(left < right) {
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}

