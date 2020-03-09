
/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
*/


// method 1: two pointers from back
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, count1 = 0;
        int j = T.length() - 1, count2 = 0;
        while(i >= 0 && j >= 0) {
            char s = S.charAt(i);
            char t = T.charAt(j);
            //System.out.println("s = " + s + ", t = " + t);
            /*
            # #, # x, x #, s s, s t
            */
            if(s == '#' && t == '#') {
                count1++;
                count2++;
                i--;
                j--;
            }
            else if(s == '#' && t != '#') {
                count1++;
                i--;
                if(count2 > 0) {
                    count2--;
                    j--;
                }
            }
            else if(s != '#' && t == '#') {
                count2++;
                j--;
                if(count1 > 0) {
                    count1--;
                    i--;
                } 
            }
            else {
                if(count1 > 0 && count2 > 0) {
                    count1--;
                    count2--;
                    i--;
                    j--;
                }
                else if(count1 > 0) {
                    count1--;
                    i--;
                }
                else if(count2 > 0) {
                    count2--;
                    j--;
                }
                else {
                    if(s != t) {
                        //System.out.println("false: s = " + s + ", t = " + t);
                        return false;
                    }
                    i--;
                    j--;
                }
            }
        }
        if(i >= 0) {
            while(i >= 0) {
                if(count1 < 0) return false;
                if(S.charAt(i) == '#') {
                    count1++;
                }
                else {
                    count1--;
                }
                i--;
            }
            return count1 >= 0;
        }
        if(j >= 0) {
            while(j >= 0) {
                if(count2 < 0) return false;
                if(T.charAt(j) == '#') {
                    count2++;
                }
                else {
                    count2--;
                }
                j--;
            }
            return count2 >= 0;
        }
        return true;
    }
}

