
/*
Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
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
// method 1: tree
// time complexity: O(n), space complexity: O(height)
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return helper(null, root);
    }
    public int helper(TreeNode parent, TreeNode curr) {
        if(curr == null) {
            return 0;
        }
        if(curr.left == null && curr.right == null && parent != null && curr == parent.left) {
            return curr.val;
        }
        int left = helper(curr, curr.left);
        int right = helper(curr, curr.right);
        return left + right;
    }
}


