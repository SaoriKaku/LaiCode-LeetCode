
/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:
Input: 121
Output: true

Example 2:
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:
Coud you solve it without converting the integer to a string?
*/


// method 1: String
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean isPalindrome(int x) {
        char[] input = (x + "").toCharArray();
        int left = 0;
        int right = input.length - 1;
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


// method 1: string + math
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x < 10) return true;
        String str = String.valueOf(x);
        int n = str.length();
        for(int i = 0; i <= n / 2; i++) {
            int last = x % 10;
            x = x / 10;
            int first = str.charAt(i) - '0';
            //System.out.println("first = " + first + ", last = " + last);
            if(first != last) {
                return false;
            }
        }
        return true;
    }
}


