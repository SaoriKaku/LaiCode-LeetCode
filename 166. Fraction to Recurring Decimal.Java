
/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
*/


// method 1: Math
// time complexity: O(n), space complexity: O(n)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0 || denominator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int sign = 1;
        long up = (long)numerator, down = (long)denominator;
        if(numerator < 0) {
            sign *= -1;
            up *= -1;
        }
        if(denominator < 0) {
            sign *= -1;
            down *= -1;
        }
        if(sign < 0) {
            sb.append("-");
        }
        sb.append(up / down);
        up = up % down;
        if(up == 0) {
            return String.valueOf(sb);
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while(up != 0) {
            if(map.containsKey(up)) {
                sb.insert(map.get(up), "(");
                sb.append(")");
                return String.valueOf(sb);
            }
            map.put(up, sb.length());
            sb.append(10 * up / down);
            up = 10 * up % down;
        }
        return String.valueOf(sb);
    }
}




