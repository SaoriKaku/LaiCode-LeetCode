
/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/


// method 1: three round, left -> right, right -> left, left -> right
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
                  1  2  3  4
    leftProduct:  1  1  2  6
    rightProduct: 24 12 4  1
    result:       24 12 8  6 
    */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if(nums == null || nums.length == 0) {
            return result;
        }
        if(nums.length == 1) {
            return nums;
        }
        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i - 1];
        }
        //System.out.println(Arrays.toString(leftProduct));
        int[] rightProduct = new int[nums.length];
        rightProduct[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }
        //System.out.println(Arrays.toString(rightProduct));
        // nums[i] = leftProduct[i] * rightProduct[i];
        for(int i = 0; i < nums.length; i++) {
            result[i] = leftProduct[i] * rightProduct[i];
        }
        return result;
    }
}



