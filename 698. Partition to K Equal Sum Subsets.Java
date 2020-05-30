
/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 
Note:
1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/

// method 1: dfs
// time complexity: O(k^n), space complexity: O(n)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        if(sum % k != 0) return false;
        sum /= k;
        Arrays.sort(nums);
        // 最大的数值>sum
        int right = nums.length - 1;
        if(nums[nums.length - 1] > sum) return false;
        while(nums[right] == sum) {
            right--;
            k--;
        }
        return dfs(new int[k], nums, right, sum);
    }
    // int[] subset: 一共k个，每个值代表每个subset的当前的所有元素和
    // index: 从最右边开始，往左移动
    public boolean dfs(int[] subsets, int[] nums, int index, int sum) {
        if(index < 0) return true;
        for(int i = 0; i < subsets.length; i++) {
            if(subsets[i] + nums[index] <= sum) {
                subsets[i] += nums[index];
                if(dfs(subsets, nums, index - 1, sum)) {
                    return true;
                }
                subsets[i] -= nums[index];
            }
        }
        return false;
    }
}

