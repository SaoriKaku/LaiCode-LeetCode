
/*
Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.

Example 1:
Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Example 2:
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false
 
Constraints:
Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
// method 1: dfs
// time complexity: O(m + n), space complexity: O(max(m, n))
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null || root2 == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        dfs(root1, set);
        return findSum(root2, set, target);
    }
    public boolean findSum(TreeNode root, Set<Integer> set, int target) {
        if(root == null) {
            return false;
        }
        if(set.contains(target - root.val)) {
            return true;
        }
        return findSum(root.left, set, target) || findSum(root.right, set, target);
    }
    public void dfs(TreeNode root, Set<Integer> set) {
        if(root == null) {
            return;
        }
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }
}






