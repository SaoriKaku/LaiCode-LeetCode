
/*
We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false
Note:

A and B will have length at most 100.
*/


// method 1: time complexity: O(2n) = O(n), space complexity: O(1) 
class Solution {
    /*
    A = xy, B = yx;
    AA = xyxy = x(B)y
    */
    public boolean rotateString(String A, String B) {
        if(A == null || B == null || A.length() != B.length()) {
            return false;
        }
        else if(A.length() == 0 && A.length() == 0) {
            return true;
        }
        else {
            return isSubString(A + A, B);
        }
    }
    public boolean isSubString(String a, String b) {
        int i = 0;
        int j = 0;
        while(i < a.length()) {
            if(a.charAt(i) == b.charAt(0)) {
                j = 0;
                while(j < b.length()) {
                    if(a.charAt(i) == b.charAt(j)){
                        i++;
                        j++;
                    }
                    else {
                        break;
                    }
                }
                if(j == b.length()) {
                    return true;
                }
            }
            else {
                i++;
            }
        }
        return false;
    }



    