
/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/


// method 1: using x ^ (x - 1) to make the least significant bit(when this bit == 1) from 1 to 0
// time compleixty: O(n * number of bit==1 in a number), space complexity: O(1)
class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        int i = 0;
        while(i < num + 1) {
            int curr = i;
            int count = 0;
            while(curr != 0) {
                curr = curr & (curr - 1);
                count++;
            }
            result[i] = count;
            i++;
        }
        return result;
    }
}


// method 2: DP + Most Significant Bit 
// time compleixty: O(n), space complexity: O(1)
class Solution {
class Solution {
    /*
    0: 0000
    1: 0001
    2: 0010 = 0在右边第二位加1 -> 2^1
    3: 0011 = 1在右边第二位加1
    4: 0100 = 0在右边第三位加1 -> 2^2
    5: 0101 = 1在右边第三位加1
    6: 0110 = 2在右边第三位加1
    7: 0111 = 3在右边第三位加1
    */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        // result[a + i] = result[i] + 1; a = 2^j
        int i = 0;
        int a = 1;
        while(a <= num) {
            while(i < a && a + i <= num) {
                result[a + i] = result[i] + 1;
                i++;
            }
            i = 0;
            a = a * 2;
        }
        return result;
    }
}


// method 3: DP + Least Significant Bit 
// time compleixty: O(n), space complexity: O(1)
class Solution {
    /*
    x= (1001011101)2 = (605)10
    x= (100101110)2 = (302)10
    result[i] = result[i / 2] + result%2
    */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        // result[i] = result[i / 2] + result%2
        for(int i = 0; i <= num; i++) {
            result[i] = result[i / 2] + i % 2;
        }
        return result;
    }
}


// method 4: DP + Last Set Bit
// time compleixty: O(n), space complexity: O(1)
class Solution {
    /*
    result[i] = result[i & (i - 1)] + 1;
    */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for(int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }
}



