
/*
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

 

Note: 

1 <= preorder.length <= 100
The values of preorder are distinct.
*/


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
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0) {
            return null;
        }
        return bstFromPreorderHelper(preorder, 0, preorder.length - 1);
    }
    
    public TreeNode bstFromPreorderHelper(int[] preorder, int left, int right) {
        if(left == right) {
           return new TreeNode(preorder[left]); 
        }
        if(left > right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[left]);
        int bound = findRightFirstIndex(preorder, left, right);
        if(bound == left) {
            root.left = null;
            root.right = bstFromPreorderHelper(preorder, left + 1, right);
        }
        else if(bound > right) {
            root.left = bstFromPreorderHelper(preorder, left + 1, right);
            root.right = null;
        }
        else {
            root.left = bstFromPreorderHelper(preorder, left + 1, bound - 1);
            root.right = bstFromPreorderHelper(preorder, bound, right);
        }
        return root;
    }
    
    public int findRightFirstIndex(int[] preorder, int left, int right) {
        int bound = left;
        while(bound <= right) {
            if(preorder[bound] > preorder[left]) {
                return bound;
            }
            bound++;
        }
        return bound;
    }
}
