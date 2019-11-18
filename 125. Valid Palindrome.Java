
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
*/


// method 1: two pointers, two round
class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        int fast = 0;
        int slow = 0;
        char[] input = s.toLowerCase().toCharArray();
        while(fast < input.length) {
            if(Character.isDigit(input[fast]) || Character.isLetter(input[fast]) ) {
                input[slow++] = input[fast++];
            }
            else {
                fast++;
            }
        }
        int left = 0;
        int right = slow - 1;
        while(left < right) {
            if(input[left] != input[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


// method 2: two pointers, one round, but time space complexity is the same as method 1
class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        char[] input = s.toLowerCase().toCharArray();
        int left = 0;
        int right = input.length - 1;
        while(left < right) {
            if(!Character.isDigit(input[left]) && !Character.isLetter(input[left])) {
                left++;
            }
            else if(!Character.isDigit(input[right]) && !Character.isLetter(input[right])) {
                right--;
            }
            else {
                if(input[left] != input[right]) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}




