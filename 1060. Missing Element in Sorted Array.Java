
/*
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

Example 1:
Input: A = [4,7,9,10], K = 1
Output: 5
Explanation: 
The first missing number is 5.

Example 2:
Input: A = [4,7,9,10], K = 3
Output: 8
Explanation: 
The missing numbers are [5,6,8,...], hence the third missing number is 8.

Example 3:
Input: A = [1,2,4], K = 3
Output: 6
Explanation: 
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 
Note:
1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
*/


// method 1: Math + Array
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        // k 要找的数字比数组中最大的数字还大
        int maxMissingCount = missingCount(nums, n-1);
        if(k > maxMissingCount) {
            return nums[n-1] + k - maxMissingCount;
        }
        // find index missing(index - 1) < k < missing(index)
        int index = 1;
        while(missingCount(nums, index) < k) {
            index++;
        }
        return nums[index - 1] + k - missingCount(nums, index - 1);
    }
    public int missingCount(int[] nums, int index) {
        /* index 0 1 2 3 
           nums: 1 4 7 9
           [0, index]有多少数字 - 数组中已经有的数字数目
           = [0, index]有多少缺少的数字
        */
        return nums[index] - nums[0] - index;
    }
}




