
/*
Given a binary tree, count the number of uni-value subtrees.
A Uni-value subtree means all nodes of the subtree have the same value.

Example :
Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
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
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = new int[1];
        countUnivalSubtreesHelper(root, result);
        return result[0];
    }
    public boolean countUnivalSubtreesHelper(TreeNode root, int[] result) {
        if(root == null) {
            return true;
        }
        boolean left = countUnivalSubtreesHelper(root.left, result);
        boolean right = countUnivalSubtreesHelper(root.right, result);
        if(left && right) {
            if(root.left != null && root.left.val != root.val) {
                return false;
            }
            if(root.right != null && root.right.val != root.val) {
                return false;
            }
            result[0]++;
            return true;
        }
        return false;
    }
}


