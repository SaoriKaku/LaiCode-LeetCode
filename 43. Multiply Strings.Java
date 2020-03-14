
/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Note:
The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/


// method 1: math + string
// time complexity: O(m * n), space complexity: O(m + n)
/*
           3  2  1  0
              1  2  3
                 4  5
        _____________
                 1  5
              1  0
              5
              1  2
              8
           4
                 
        */
public String multiply(String num1, String num2) {
    int len1 = num1.length();
    int len2 = num2.length();
    int n = len1 + len2;
    int[] digits = new int[n];
    for(int i = len1 - 1; i >= 0; i--) {
        char char1 = num1.charAt(i);
		int digit1 = (int)(char1 - '0');
		for(int j = len2 - 1; j >= 0; j--) {
            char char2 = num2.charAt(j);
            int digit2 = (int)(char2 - '0');
            int product = digit1 * digit2;
            int k = i + j + 1;
            product += digits[k];
            digits[k] = product % 10;
            digits[k - 1] += product / 10;
        }
    }
    StringBuilder number = new StringBuilder();
    for(int curr: digits) {
        if(number.length() == 0 && curr == 0) {
            continue;
        }
        number.append(curr);
    }
    String result = new String(number);
    return result.length() == 0 ? "0" : String.valueOf(result);
}

