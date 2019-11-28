
/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
*/


class Solution {
    /*
    加法分三种情况：
    0 + 0 = 0   ^即可满足
    0 + 1 = 1   ^即可满足
    1 + 1 = 10  &以后向左移动一个位置
    终止条件：知道&这两个数字的结果为0，说明没有1可以向前进位了

    a=5 0101
    b=6 0110
    5&6 0100  5&6<<1 1000 -> add -> b
    5^6 0011              -> a
    
    a=3 0011
    b=8 1000
    3&8 0000  3&8<<1 0000 -> add -> b
    3^8 1011  -> a

    5 + 6 = 3 + 8 = 11 + 0
    */
    public int getSum(int a, int b) {
        while(b != 0) {
            int add = (a & b) << 1;
            a = a ^ b;
            b = add;
            // System.out.println(a);
            // System.out.println(b);
        }
        return a;
    }
}



