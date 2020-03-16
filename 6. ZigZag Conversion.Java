
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
*/


// method 1: string
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    A   G
    B F H
    C E I
      D
    */
    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows == 0) {
            return "";
        }
        StringBuilder[] str = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++) {
            str[i] = new StringBuilder();
        }
        int index = 0, n = s.length();
        while(index < n) {
            for(int i = 0; i < numRows; i++) {
                if(index == n) break;
                str[i].append(s.charAt(index++));
            }
            for(int i = numRows - 2; i >= 1; i--) {
                if(index == n) break;
                str[i].append(s.charAt(index++));
            }
        }
        for(int i = 1; i < numRows; i++) {
            str[0].append(str[i]);
        }
        return String.valueOf(str[0]);
    }
}




