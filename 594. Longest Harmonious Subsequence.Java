
/*
We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Note: The length of the input array will not exceed 20,000.
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int findLHS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.containsKey(num - 1)) {
                result = Math.max(result, map.get(num) + map.get(num - 1));
            }
            if(map.containsKey(num + 1)) {
                result = Math.max(result, map.get(num) + map.get(num + 1));
            }
        }
        return result;
    }
}


// method 2: sort + two pointers
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    public int findLHS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0, right = 0, result = 0;
        while(left < nums.length) {
            while(right < nums.length && nums[right] - nums[left] <= 1) {
                right++;
            }
            if(nums[right - 1] - nums[left] == 1) {
                result = Math.max(result, right - left);
            }
            left++;
        }
        return result;
    }
}

