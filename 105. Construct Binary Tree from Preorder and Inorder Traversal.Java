
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:You may assume that duplicates do not exist in the tree.

For example, given
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
*/


// method 1: recursion
// time complexity: O(n * n = n^2), space complexity: O(height of tree)
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    /*
    preorder: pre = preLeft = [pre, preRight - 1], preRight = [preRight, preorder.length - 1]
    inorder: inLeft[0, in - 1], in, inRight = [in + 1, inorder.length - 1]
     */
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        return buildTreeHelper(preorder, 0, 0, inorder, 0, inorder.length - 1);
    }
    public TreeNode buildTreeHelper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if(preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int value = preorder[preLeft];
        TreeNode root = new TreeNode(value);
        //System.out.println("root = " + root.val);
        int inorderIndex = searchInorder(inorder, inLeft, inRight, value); 
        preRight = preLeft + (inorderIndex - inLeft) + 1;
        root.left = buildTreeHelper(preorder, preLeft + 1, preRight - 1, inorder, inLeft, inorderIndex - 1);
        root.right = buildTreeHelper(preorder, preRight, preorder.length - 1, inorder, inorderIndex + 1, inRight);
        return root;
    }
    public int searchInorder(int[] inorder, int inLeft, int inRight, int target) {
        for(int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }
}


// method 2: optimized method 1 recursion
// time complexity: O(n), space complexity: O(n)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    /*
    preorder: pre = preLeft = [pre, preRight - 1], preRight = [preRight, preorder.length - 1]
    inorder: inLeft[0, in - 1], in, inRight = [in + 1, inorder.length - 1]
     */
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, 0, inorderMap, 0, inorder.length - 1);
    }
    public TreeNode buildTreeHelper(int[] preorder, int preLeft, int preRight, HashMap<Integer, Integer> inorderMap, int inLeft, int inRight) {
        if(preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int value = preorder[preLeft];
        TreeNode root = new TreeNode(value);
        //System.out.println("root = " + root.val);
        int inorderIndex = inorderMap.get(value);
        preRight = preLeft + (inorderIndex - inLeft) + 1;
        root.left = buildTreeHelper(preorder, preLeft + 1, preRight - 1, inorderMap, inLeft, inorderIndex - 1);
        root.right = buildTreeHelper(preorder, preRight, preorder.length - 1, inorderMap, inorderIndex + 1, inRight);
        return root;
    }
}



