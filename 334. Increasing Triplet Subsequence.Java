
/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:
Input: [1,2,3,4,5]
Output: true

Example 2:
Input: [5,4,3,2,1]
Output: false
*/


// method 1: min and second min
// time complexity: O(n), space complexity: O(1)
class Solution {
    // update the smallest and second smallest element
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length <= 2) {
            return false;
        }
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num: nums) {
            if(first >= num) {
                first = num;
            }
            else if(second >= num) {
                second = num;
            }
            else {
                return true;
            }
        }
        return false;
    }
}


