
/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
*/


// time complexity: O(32), space complexity: O(1)
class Solution {
    public int hammingDistance(int x, int y) {
        int mask = 1;
        int count = 0;
        for(int i = 0; i < 32; i++) {
            int m = (x >> i) & mask;
            //System.out.println("m = " + m);
            int n = (y >> i) & mask;
            //System.out.println("n = " + n);
            if((m ^ n) == 1) {
                count++;
            }
        }
        return count;
    }
}


// time complexity: O(32), space complexity: O(1)
class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        for(int i = 0; i < 32; i++) {
            if(((xor >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}



