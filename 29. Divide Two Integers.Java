
/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
Return the quotient after dividing dividend by divisor.
The integer division should truncate toward zero.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3

Example 2:
Input: dividend = 7, divisor = -3
Output: -2

Note:
Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
*/


// method 1: bit
// time complexity: O(logn), space complexity: O(1)
class Solution {
    /*
    32 / 3
    3 < 32, 6 < 32, 12 < 32, 24 < 32, 48 > 32, 2^(4-1) = 8
    32 - 24 = 8
    3 < 8, 6 < 8, 12 > 8, 2^(2-1) = 2
    */
    public int divide(int dividend, int divisor) {
        if(divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if(dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }
        int sign = 1;
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }
        int result = 0;
        long x = (long)dividend;
        x = x < 0 ? -1 * x : x;
        long y = (long)divisor;
        y = y < 0 ? -1 * y : y;
        while(x >= y) {
            int digit = 0;
            while(x >= y << digit) {
                digit++;
            }
            result += (1 << (digit - 1));
            x -= (y << (digit - 1));
            //System.out.println("result = " + result + ", x = " + x);
        }
        return sign * result;
    }
}


// method 2: binary search
// time complexity: O(log(dividend / divisor)), space complexity: O(1)
class Solution {
    /*
    1. find the smaller integer bigger than dividend -> divisor * 2 > dividend
    2. find the largest integer result -> result * divisor <= dividend 
    */
    public int divide(int dividend, int divisor) {
        int sign = 1;
        long dividendLong = (long)dividend;
        long divisorLong = (long)divisor;
        if(dividendLong < 0) {
            sign *= -1;
            dividendLong *= -1;
        }
        if(divisorLong < 0) {
            sign *= -1;
            divisorLong *= -1;
        }
        long right = 1;
        while(divisorLong * right < dividendLong) {
            right *= 2;
        }
        //System.out.println("right = " + right);
        long left = 0;
        while(left + 1 < right) {
            long mid = left + ((right - left) >> 1);
            //System.out.println("mid = " + mid + ", left = " + left  + ", right = " + right);
            if(mid * divisorLong == dividendLong) {
                return (int)mid;
            }
            else if(mid * divisorLong < dividendLong) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        if(divisorLong * right <= dividendLong) {
            long result = sign * right;
            if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int)result;
        }
        return sign * (int)left;
    }
}



