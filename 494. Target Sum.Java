
/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/


// method 1: backtracking
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int[] count = new int[1];
        dfs(nums, 0, S, count);
        return count[0];
    }
    public void dfs(int[] nums, int index, int target, int[] count) {
        if(index == nums.length) {
            if(target == 0) {
                count[0]++;
            }
            return;
        }
        dfs(nums, index + 1, target + nums[index], count);
        dfs(nums, index + 1, target - nums[index], count);
    }
}


// method 2: dfs + memorization
// time complexity: O(nums.length * (2 * sum + 1)), space complexity: O(nums.length * (2 * sum + 1))
class Solution {
    /* [i][j]: start from index i, how many ways to form sum = j
    size: [nums.length][2 * sum + 1]
    index -> current sum, [-sum, 0, sum] -> [0, sum, 2*sum + 1]
    dfs: return how many ways to when sum = target
    */
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        int[][] memo = new int[nums.length][2 * sum + 1];
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < 2 * sum + 1; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(nums, S, 0, sum, memo);
    }
    public int dfs(int[] nums, int target, int index, int sum, int[][] memo) {
        if(index == nums.length) {
            return target == 0 ? 1 : 0;
        }
        if(target > sum || target < -sum) {
            return 0;
        }
        // for the nums from index to last to form sum = target, 
        // index j = target + sum, from start at -sum to start at 0
        if(memo[index][target + sum] == -1) {
            return dfs(nums, target - nums[index], index + 1, sum - nums[index], memo) + dfs(nums, target + nums[index], index + 1, sum - nums[index], memo);
        }
        return memo[index][target + sum];
    }
}


