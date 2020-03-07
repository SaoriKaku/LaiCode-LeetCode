
/*
You are installing a billboard and want it to have the largest height.  The billboard will have two steel supports, one on each side.  Each steel support must be an equal height.
You have a collection of rods which can be welded together.  For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
Return the largest possible height of your billboard installation.  If you cannot support the billboard, return 0.

Example 1:
Input: [1,2,3,6]
Output: 6
Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.

Example 2:
Input: [1,2,3,4,5,6]
Output: 10
Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.

Example 3:
Input: [1,2]
Output: 0
Explanation: The billboard cannot be supported, so we return 0.

Note:
0 <= rods.length <= 20
1 <= rods[i] <= 1000
The sum of rods is at most 5000.
*/


// method 1: dfs
// time complexity: O(3^n), space complexity: O(n)
class Solution {
    public int tallestBillboard(int[] rods) {
        if(rods == null || rods.length == 0) {
            return 0;
        }
        int[] result = new int[1];
        dfs(rods, 0, 0, 0, result);
        return result[0];
    }
    public void dfs(int[] rods, int level, int left, int right, int[] result) {
        if(level == rods.length) {
            if(left == right) {
                result[0] = Math.max(result[0], left);
            }
            return;
        }
        dfs(rods, level + 1, left + rods[level], right, result);
        dfs(rods, level + 1, left, right + rods[level], result);
        dfs(rods, level + 1, left, right, result);
    }
}


// method 2: 2dfs + HashMap
// time complexity: O(3^(n/2)), space complexity: O(3^(n/2))
class Solution {
    public int tallestBillboard(int[] rods) {
        if(rods == null || rods.length == 0) {
            return 0;
        }
        // <key = diff = leftSum - rightSum, value = max leftSum>
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        dfs(rods, 0, (rods.length + 1) / 2, 0, 0, leftMap);
        dfs(rods, (rods.length + 1) / 2, rods.length, 0, 0, rightMap);
        // System.out.println(leftMap);
        // System.out.println(rightMap);
        int result = 0;
        for(Map.Entry<Integer, Integer> leftEntry: leftMap.entrySet()) {
            int minusDiff = -1 * leftEntry.getKey();
            if(rightMap.containsKey(minusDiff)) {
                result = Math.max(result, leftEntry.getValue() + rightMap.get(minusDiff));
            }
        }
        return result;
    }
    public void dfs(int[] rods, int index, int end, int left, int right, Map<Integer, Integer> map) {
        if(index == end) {
            int diff = left - right;
            map.put(diff, Math.max(map.getOrDefault(diff, 0), left));
            return;
        }
        dfs(rods, index + 1, end, left + rods[index], right, map);
        dfs(rods, index + 1, end, left, right + rods[index], map);
        dfs(rods, index + 1, end, left, right, map);
    }
}


