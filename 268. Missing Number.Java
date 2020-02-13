
/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:
Input: [3,0,1]
Output: 2

Example 2:
Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note: Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/


// method 1: math
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int missingNumber(int[] nums) {
        // method 1: math
        int sum = 0;
        for(int curr: nums) {
            sum += curr;
        }
        int fullSum = (nums.length + 1) * nums.length / 2;
        return fullSum - sum;
    }
}


// method 2: HashSet
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int missingNumber(int[] nums) {
        // method 2: HashSet
        Set<Integer> set = new HashSet<>();
        for(int curr: nums) {
            set.add(curr);
        }
        for(int i = 0; i <= nums.length; i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}


// method 3: bit
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i <= nums.length; i++) {
            result ^= i;
        }
        for(int num: nums) {
            result ^= num;
        }
        return result;
    }
}

