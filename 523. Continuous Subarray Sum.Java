
/*
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 
Constraints:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/


// method 1: HashMap + prefixSum
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    sum[i,j] = (sum[0,i] - sum[0,j-1]) % k == 0
    sum[0, i] % k == sum[0, j-1] % k
    if k == 0, find currSum in map
    else k != 0, find currSum % k in map
    */
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        // key: sum, value: last index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int currSum = 0;
        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if(k != 0) {
                currSum %= k;
            }
            if(map.containsKey(currSum) && i - map.get(currSum) >= 2) {
                return true;
            }
            map.putIfAbsent(currSum, i);
        }
        return false;
    }
}




