
/*
Given an integer n, return the number of trailing zeroes in n!.

Example 1:
Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
*/


// method 1: Math
// time complexity: O(logn), space complexity: O(1)
class Solution {
    // count how many 5 in n
    public int trailingZeroes(int n) {
        int count = 0;
        while(n > 0) {
            n = n / 5;
            count += n;
        }
        return count;
    }
}




