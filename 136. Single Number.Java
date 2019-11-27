
/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4
*/


// method 1: set + 1round
// time compleixty: O(n), space complextiy: O(n)
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int result = 0;
        for(int curr: nums) {
            if(set.add(curr)) {
                result += curr;
            }
            else {
                result -= curr;
            }
        }
        return result;
    }
}


// method 2: bit 
// If we take XOR of zero and some bit, it will return that bit
// If we take XOR of two same bits, it will return 0
// time compleixty: O(n), space complextiy: O(1)
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int curr: nums) {
            result ^= curr;
        }
        return result;
    }
}



