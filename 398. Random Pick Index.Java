
/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/


// method 1: random, nums.length == n, number of target == k
// time complexity: O(n - k), space complexity: O(1)
class Solution {
    public int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }
    /*
    pick one number = 1/n
    */
    public int pick(int target) {
        Random random  = new Random();
        int randomIndex = random.nextInt(nums.length);
        while(nums[randomIndex] != target) {
            randomIndex = random.nextInt(nums.length);
        }
        return randomIndex;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */



