
/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note: You may assume that duplicates do not exist in the tree.

For example, given
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/


// method 1: recursion + hashmap
// time complexity: O(n), space complexity: O(height of tree) + O(n)
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(inorderMap, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    public TreeNode buildTreeHelper(Map<Integer, Integer> inorderMap, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if(inLeft > inRight) {
            return null;
        }
        int value = postorder[postRight];
        TreeNode root = new TreeNode(value);
        int inorderIndex = inorderMap.get(value);
        root.left = buildTreeHelper(inorderMap, inLeft, inorderIndex - 1, postorder, postLeft, postLeft + inorderIndex - inLeft - 1);
        root.right = buildTreeHelper(inorderMap, inorderIndex + 1, inRight, postorder, postLeft + inorderIndex - inLeft, postRight - 1);
        return root;
    }
}

