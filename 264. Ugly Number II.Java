
/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:
Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:  
1 is typically treated as an ugly number.
n does not exceed 1690.
*/


// method 1: math
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    store result, keep i2, i3, i5
    choose next smallest in n2, n3, n5
    if next == n2/n3/n5, i2++/i3++/i5++
    */
    public int nthUglyNumber(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        while(result.size() < n) {
            int n2 = result.get(i2) * 2;
            int n3 = result.get(i3) * 3;
            int n5 = result.get(i5) * 5;
            int min = Math.min(n2, Math.min(n3, n5));
            result.add(min);
            if(min == n2) {
                i2++;
            }
            if(min == n3) {
                i3++;
            }
            if(min == n5) {
                i5++;
            }
        }
        return result.get(n-1);
    }
}


