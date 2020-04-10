
/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: [5,7]
Output: 4

Example 2:
Input: [0,1]
Output: 0
*/


// method 1: bit
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    这两个数的前i位是一样的，所以&以后也是一样的；i后面bits是不一样的，在&以后会变成0，我们要找到n是多少，
    把m n向右移动，直到m = n，所以现在m n所有bit都是一样的，
    假设现在移动了i位，再把m或者n向左移动刚才向右移动的i位，就是最后的结果。
    */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while(m < n) {
            m = m >> 1;
            n = n >> 1;
            shift++;
        }
        return m << shift;
    }
}


