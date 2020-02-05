
/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

Example 1:
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:
Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 
Note:
The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
*/


// method 1: do it as a tree, not take advantage of BST
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
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) {
            return 0;
        }
        int[] sum = new int[1];
        rangeSumBSTHelper(root, L, R, sum);
        return sum[0];
    }
    public void rangeSumBSTHelper(TreeNode root, int L, int R, int[] sum) {
        if(root == null) {
            return;
        }
        rangeSumBSTHelper(root.left, L, R, sum);
        rangeSumBSTHelper(root.right, L, R, sum);
        if(root.val >= L && root.val <= R) {
            sum[0] += root.val;
        }
    }
}


// method 2: take advantage of BST to prune branches
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
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) {
            return 0;
        }
        int[] sum = new int[1];
        rangeSumBSTHelper(root, L, R, sum);
        return sum[0];
    }
    public void rangeSumBSTHelper(TreeNode root, int L, int R, int[] sum) {
        if(root == null) {
            return;
        }
        if(root.val >= L && root.val <= R) {
            sum[0] += root.val;
        }
        if(root.val < R) {
             rangeSumBSTHelper(root.right, L, R, sum);
        }
        if(root.val > L) {
            rangeSumBSTHelper(root.left, L, R, sum);
        }
    }
}


// method 3: iterative: preorder
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
    public int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if(curr.val >= L && curr.val <= R) {
                result += curr.val;
                if(curr.left != null) {
                    stack.push(curr.left);
                }
                if(curr.right != null) {
                    stack.push(curr.right);
                }
            }
            else if(curr.val >= L && curr.left != null) {
                stack.push(curr.left);
            }
            else if(curr.val <= R && curr.right != null) {
                stack.push(curr.right);
            }
        }
        return result;
    }
}
