
/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
*/


// method 1: String 
// time complexity: O(max(a, b)), space complexity: O(max(a, b))
class Solution {
    public String addBinary(String a, String b) {
        if(a == null || a.length() == 0) {
            return b;
        }
        if(b == null || b.length() == 0) {
            return a;
        }
        StringBuilder result = new StringBuilder();
        boolean addOne = false;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while(i >= 0 && j >= 0) {
            char currA = a.charAt(i);
            char currB = b.charAt(j);
            char curr = addOne ? '1' : '0';
            addOne = false;
            if(currA == '1' && currB == '1') {
                addOne = true;
            }
            else if(currA != currB) {
                addOne = curr == '1' ? true : false;
                curr = curr == '1' ? '0' : '1';
            }
            result.append(curr);
            i--;
            j--;
        }
        int k = i < 0 ? j : i;
        String c = a.length() > b.length() ? a : b;
        while(k >= 0) {
            char currC = c.charAt(k);
            char curr = addOne ? '1' : '0';
            addOne = false;
            if(curr == '1' && currC == '1') {
                curr = '0';
                addOne = true;
            }
            else if(curr != currC) {
                curr = '1';
            }
            result.append(curr);
            k--;
        }
        if(addOne) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}


// method 2: Optimized String 
// time complexity: O(max(a, b)), space complexity: O(max(a, b))
class Solution {
    public String addBinary(String a, String b) {
        if(a == null || a.length() == 0) {
            return b;
        }
        if(b == null || b.length() == 0) {
            return a;
        }
        StringBuilder result = new StringBuilder();
        boolean addOne = false;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while(i >= 0 || j >= 0) {
            char currA = i >= 0 ? a.charAt(i) : '0';
            char currB = j >= 0 ? b.charAt(j) : '0';
            char curr = addOne ? '1' : '0';
            addOne = false;
            if(currA == '1' && currB == '1') {
                addOne = true;
            }
            else if(currA != currB) {
                addOne = curr == '1' ? true : false;
                curr = curr == '1' ? '0' : '1';
            }
            result.append(curr);
            i--;
            j--;
        }
        if(addOne) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}


