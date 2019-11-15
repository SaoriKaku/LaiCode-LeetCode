
/*
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
*/


// time complexity: O(n), space complexity: O(128)
class Solution {
    /*
    idea: number of char if count is odd <= 1, return true; otherwise, return false
    */
    public boolean canPermutePalindrome(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        int[] dict = new int[128];
        int count = 0;
        for(char curr: s.toCharArray()) {
            int index = curr;
            dict[index]++;
            // from even to odd
            if(dict[index] % 2 == 1) {
                count++;
            }
            // from odd to even
            else {
                count--;
            }
        }
        return count <= 1;
    }
}




