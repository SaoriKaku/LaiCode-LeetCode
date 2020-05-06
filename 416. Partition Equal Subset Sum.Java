
/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
 
Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 
Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/


// method 1: DP(2D)
// time complexity: O(m * n), space complexity: O(m * n)
class Solution {
/*
if sum % 2 == 1, return false;
dp[][] = []
state: [i, j], whether sum=j of any numbers could be find from nums[0] to nums[i]
induction rule: [1, 2, 3]
不用当前nums[i], dp[i][j] = dp[i-1][j], 之前的nums[0]到nums[i-1]可以构成sum
选用当前nums[i], dp[i][j] = dp[i-1][j-nums[i]], 看之前nums[0]到nums[i-1]的和sum=j-nums[i]是否成立
       0  1  2  3  
    0  T  F  F  F 
    1  T  T  F  F  
    2  T  T  T  T 
    3  T  T  T  T
*/
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        sum = sum / 2;
        Arrays.sort(nums);
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for(int i = 0; i < nums.length + 1; i++) {
            dp[i][0] = true;
        }
        for(int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }
        for(int i = 1; i < nums.length + 1; i++) {
            for(int j = 1; j < sum + 1; j++) {
                if(nums[i-1] <= j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][sum];
    }
}


