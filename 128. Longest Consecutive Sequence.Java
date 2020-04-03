
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
Your algorithm should run in O(n) complexity.

Example:
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/


// method 1: sort
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 1, result = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1] + 1) {
                count++;
                result = Math.max(result, count);
            }
            else if(nums[i] != nums[i-1]) {
                count = 1;
            }   
        }
        return result;
    }
}


// method 2: HashMap
// time complexity: O(n), space complexity: O(n)
/* map<key: nums[i], value: number of consecutive sequence when nums[i] at left or right>
       [nums[i], ...] = count, [..., nums[i]] = count
       idea: 
       1. nums[i] in map, duplicate in nums, continue
       2. nums[i] - 1 in map, [left, ..., nums[i] - 1, nums[i]], map.get(left)++, map.get(nums[i] - 1)++;
       3. nums[i] + 1 in map, [nums[i], nums[i] + 1, ... right], map.get(nums[i])++, map.get(right)++;
       4. nums[i] - 1 && nums[i] + 1 in map, 
       [left, ... , right], map.get(left) = map.get(right) = right - left + 1, 
       how to find left and right:
       left = nums[i] - map.get(nums[i] - 1);
       right = nums[i] + map.get(nums[i] + 1);
    */
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                continue;
            }
            int left = map.containsKey(nums[i] - 1) ? map.get(nums[i] - 1) : 0;
            int right = map.containsKey(nums[i] + 1) ? map.get(nums[i] + 1) : 0;
            int range = left + right + 1;
            map.put(nums[i], range);
            map.put(nums[i] - left, range);
            map.put(nums[i] + right, range);
            result = Math.max(result, range);
            //System.out.println(map);
        }
        return result;
    }
}


// method 3: HashSet
// time complexity: O(n), space complexity: O(n)
/*
    idea: put all nums[i] in a set
    while curr - 1 in set, sum++;
    while curr + 1 in set, sum++;
    update result
    */
class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }
        int result = 1;
        for(int i = 0; i < nums.length; i++) {
            int left = nums[i] - 1, right = nums[i] + 1, sum = 1;
            while(set.contains(left)) {
                set.remove(left);
                left--;
                sum++;
            }
            while(set.contains(right)) {
                set.remove(right);
                right++;
                sum++;
            }
            result = Math.max(result, sum);
        }
        return result;
    }
}





