
/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/


// method 1: DFS + prefixSum
// time complexity: O(n), space complexity: O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        // map<key = sum, value = how many time this sum appears>
        Map<Integer, Integer> map = new HashMap<>();
        return pathSumHelper(root, sum, 0, map);
    }
    public int pathSumHelper(TreeNode root, int target, int sum, Map<Integer, Integer> map) {
        if(root == null) {
            return 0;
        }
        sum += root.val;
        int count = map.getOrDefault(sum - target, 0);
        if(sum == target) {
            count++;
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += pathSumHelper(root.left, target, sum, map);
        count += pathSumHelper(root.right, target, sum, map);
        map.put(sum, map.get(sum) - 1);
        return count;
    }
}



