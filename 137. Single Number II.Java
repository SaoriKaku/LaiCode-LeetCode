
/*
Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
*/


// method1: set + 1round
// time compleixty: O(n), space compleixty: O(n)
class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        long setSum = 0;
        long numSum = 0;
        for(int curr: nums) {
            if(set.add(curr)) {
                setSum += curr;
            }
            numSum += curr;
        }
        return (int)((3 * setSum - numSum) / 2);
    }
}


// method2: map + 2round
// time compleixty: O(n), space compleixty: O(n)
class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int curr: nums) {
            Integer count = map.get(curr);
            if(count == null) {
                map.put(curr, 1);
            }
            else {
                map.put(curr, count + 1);
            }
        }
        for(int curr: nums) {
            if(map.get(curr) == 1) {
                return curr;
            }
        }
        return 0;
    }
}


// method 3: bit + 2round
// time compleixty: O(32n), space compleixty: O(1)
class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            int mask = 1;
            for(int curr: nums) {
                // count the number of bit at i
                // move bit i to rightmost
                sum += (curr >> i) & mask;
            }
            // sum == 0 or 1
            sum = sum % 3;
            // move bit i from rightmost to i
            result = result | (sum << i);
        }
        return result;
    }
}



