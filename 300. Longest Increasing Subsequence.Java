
/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/


/*
    state: dp[i]: the max increasing subsequence including nums[i]
    induction rule: j = [0, i), if(nums[i] > nums[j]) dp[i] = max(dp[i], dp[j] + 1) ;
    */
// method 1: DP(1D)
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, result = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        //System.out.println(Arrays.toString(dp));
        return result;
    }
}


// method 2: Binary Search
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    /*
    build a sorted(increasing) array including nums[i]
    j = binary search the smallest num greater than nums[i]
    len = j + 1, update result = max(result, len)
    */
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, rightIndex = 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1; i < n; i++) {
            int insertIndex = binarySearchSmallestBigger(dp, 0, rightIndex, nums[i]);
            //System.out.println("insertIndex = " + insertIndex);
            if(insertIndex < 0) {
                insertIndex = 0;
            }
            dp[insertIndex] = nums[i];
            if(insertIndex == rightIndex + 1) {
                rightIndex++;
            }
            //System.out.println(Arrays.toString(dp));
        }
        return rightIndex + 1;
    }
    public int binarySearchSmallestBigger(int[] nums, int left, int right, int target) {
        //System.out.println("left = " + left + ", right = " + right);
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        //System.out.println("left = " + left + ", right = " + right);
        if(nums[right] < target) return right + 1;
        if(nums[left] > target) return left - 1;
        return nums[left] >= target ? left : right;
    }
}




