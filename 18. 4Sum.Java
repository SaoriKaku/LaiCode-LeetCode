
/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
The solution set must not contain duplicate quadruplets.

Example:
Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/


// method 1: sort + two pointers + deduplication
// time complexity: O(nlogn + n^3), space complexity: O(1)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));
        int n = nums.length;
        for(int i = 0; i < n-3; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i + 1; j < n-2; j++) {
                if(j > i + 1 && nums[j] == nums[j-1]) continue;
                int left = j + 1;
                int right = n - 1;
                while(left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    //System.out.println("nums[i] = " + nums[i] + ", nums[j] = " + nums[j] + ", nums[left] = " + nums[left] + ", nums[right] = " + nums[right]);
                    if(sum == target) {
                        List<Integer> curr = new ArrayList<>();
                        curr.add(nums[i]);
                        curr.add(nums[j]);
                        curr.add(nums[left]);
                        curr.add(nums[right]);
                        result.add(curr);
                        while(left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while(left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                    else if(sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }//while
            }//j
        }//i
        return result;
    }
}



