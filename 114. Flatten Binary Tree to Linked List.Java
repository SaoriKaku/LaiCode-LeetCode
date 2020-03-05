
/*
Given a binary tree, flatten it to a linked list in-place.
For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/


// method 1: recursive, postorder
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
    public TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) {
           return; 
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}


// method 2: iterative
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
    
    public void flatten(TreeNode root) {
        if(root == null) {
           return; 
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
            if(!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }
}


// method 3: recursive, preorder
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
    public void flatten(TreeNode root) {
        /*
         2    3   2
        / \    \   \ 
       3   4    4   3   2.left = null
       return 4 -> if 4 == null return 3 -> if 3 == null return 2
        */
        if(root == null) {
            return;
        }
        flattenHelper(root);
    }
    public TreeNode flattenHelper(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode leftLast = flattenHelper(root.left);
        TreeNode rightLast = flattenHelper(root.right);
        if(leftLast != null) {
            // not rightLast, 4.right = 5
            leftLast.right = root.right; 
            // is root.left, not leftLast, 1.right = 2
            root.right = root.left; 
            root.left = null;
        }
        if(rightLast != null) {
            return rightLast;
        }
        if(leftLast != null) {
            return leftLast;
        }
        return root;
    }
}



