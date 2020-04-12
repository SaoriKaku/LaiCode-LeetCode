
/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true

Example 2:
Input: nums = [1,0,1,1], k = 1, t = 2
Output: true

Example 3:
Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
*/


// method 1: TreeSet
// time complexity: O(nlogk), space complexity: O(k)
class Solution {
    /*
    TreeSet: balanced binary search tree
    set.ceiling(num), 比num大的最小值；set.floor(num), 比num小的最大值
    */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++) {
            Integer ceiling = set.ceiling(nums[i]);
            if(ceiling != null) {
                long diff = (long)ceiling - (long)nums[i];
                if(diff <= t) {
                    return true;
                }
            }
            Integer floor = set.floor(nums[i]);
            if(floor != null) {
                long diff = (long)nums[i] - (long)floor;
                if(diff <= t) {
                    return true;
                }
            }
            set.add(nums[i]);
            if(i >= k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}


// method 2: bucket
// time complexity: O(n), space complexity: O(k)
class Solution {
    /*
    bucket index = (nums[i] - min) / (t + 1), t + 1因为有可能t = 0
    check if the same or next or previous bucket has number 
    */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }
        int min = nums[0];
        for(int curr: nums) {
            min = Math.min(min, curr);
        }
        Map<Long, Integer> map = new HashMap<>();
        long diff = (long)(t + 1);
        for(int i = 0; i < nums.length; i++) {
            long index = ((long)nums[i] - (long)min) / diff;
            if(map.containsKey(index)) {
                return true;
            } 
            if(map.containsKey(index - 1) && nums[i] - map.get(index - 1) < diff) {
                return true;
            } 
            if(map.containsKey(index + 1) && map.get(index + 1) - nums[i] < diff) {
                return true;
            }
            map.put(index, nums[i]);
            if(i >= k) {
                index = ((long)nums[i-k] - (long)min) / diff;
                map.remove(index);
            }
        }
        return false;
    }
}


