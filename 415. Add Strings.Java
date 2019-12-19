
/*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/


// method 1: String
// time complexity: O(max(m, n)), space complexity: O(1)
class Solution {
    public String addStrings(String num1, String num2) {
        if(num1 == null || num1.length() == 0) {
            return num2;
        }
        if(num2 == null || num2.length() == 0) {
            return num1;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        boolean addOne = false;
        StringBuilder result = new StringBuilder();
        while(i >= 0 || j >= 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            // System.out.println("a = " + a);
            // System.out.println("b = " + b);
            int c = addOne ? a + b + 1 : a + b;
            addOne = false;
            if(c >= 10) {
                result.append(c - 10);
                addOne = true;
            }
            else {
                result.append(c);
            }
            //System.out.println("c = " + c);
            i--;
            j--;
        }
        if(addOne) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}



