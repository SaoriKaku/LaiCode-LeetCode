
/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/


// method 1: use an array to store the reversed number
// time complextiy: O(n), space complexity: O(n)
class Solution {
    public int reverse(int x) {
        boolean negative = false;
        if(x < 0) {
            negative = true;
        }
        x = Math.abs(x);
        List<Integer> num = new ArrayList<>();
        while(x > 0) {
            num.add(x % 10);
            x = x / 10;
        }
        // 123 -> 321
        int result = 0;
        for(int i = 0; i < num.size(); i++) {
            // i = 0, 2 - 0 = 2
            // i = 1, 2 - 1 = 1
            // i = 2, 2 - 2 = 0
            result += num.get(i) * Math.pow(10, num.size() - 1 - i);
            if(result == Integer.MAX_VALUE) {
                return 0;
            }
        }
        if(negative) {
            result *= -1;
        }
        return result;
    }
}


// method 2: while % / already reverse the nunber, judge whether result > Integer.MAX_VALUE/10
// time complextiy: O(n), space complexity: O(1)
class Solution {
    public int reverse(int x) {
        boolean negative = false;
        if(x < 0) {
            negative = true;
        }
        x = Math.abs(x);
        int result = 0;
        // 123 -> 321
        while(x > 0) {
            if(result > Integer.MAX_VALUE/10) {
                return 0;
            }
            int num = x % 10;
            x = x / 10;
            result = 10 * result + num; 
        }
        if(negative) {
            result *= -1;
        }
        return result;
    }
}
