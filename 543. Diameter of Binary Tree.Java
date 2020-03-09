
/*
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
*/


// method 1: postorder
// time complexity: O(n), space complexity: O(height of tree)
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
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = new int[1];
        helper(root, result);
        return result[0] - 1;
    }
    public int helper(TreeNode root, int[] result) {
        if(root == null) {
            return 0;
        }
        int left = helper(root.left, result);
        int right = helper(root.right, result);
        result[0] = Math.max(result[0], left + right + 1);
        return Math.max(left, right) + 1;
    }
}


