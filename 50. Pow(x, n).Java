
/*
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/

// time complexity: O(n) time out
// space complexity: O(1)
class Solution {
    public double myPow(double x, int n) {
        // brute force
        if(n < 0) {
            x = 1 / x;
            n *= -1;
        }
        //System.out.println("x = " + x);
        double result = 1;
        for(int i = 0; i < n; i++) {
            result *= x;
            //System.out.println("result = " + result);
        }
        return result;
    }
}

// method: recursion
// time complexity: O(logn)
// space complexity: O(logn)
class Solution {
    public double fastPow(double x, int n) {     
        if(n == 0) {
            return 1.0;
        }
        double harfX = fastPow(x, n / 2);
        if(n % 2 == 0) {
            return harfX * harfX;
        }
        else {
            return harfX * harfX * x;
        }
    }
    public double myPow(double x, int n) {
        if(n < 0) {
            x = 1 / x;
            n *= -1;
        }
        if(n == Integer.MIN_VALUE) {
            n = n / 2;
            x *= x;
        }
        return fastPow(x, n);
    }
}


// method: bottom up
// time complexity: O(logn)
// space complexity: O(1)
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) {
            return 1.0;
        }
        long N = n;
        if(N < 0) {
            N *= -1;
            x = 1 / x;
        }
        double result = 1;
        double curr = x;
        for(long i = N; i > 0; i = i / 2) {
            if((i % 2) == 1) {
                result = result * curr;
            }
            curr = curr * curr;
        }
        return result;
    }
    
}





