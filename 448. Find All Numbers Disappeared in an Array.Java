
/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]
*/


// method 1: the relationship between array and index
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*
        index  0  1  2  3  4  5  6  7
        array  4  3  2  7  8  2  3  1
              -4 -3 -2 -7       -3 -1
       result                 5  6
        */
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}


