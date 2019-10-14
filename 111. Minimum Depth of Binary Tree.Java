
/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
*/

// recursive: 
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
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(root.left == null && root.right == null) {
            return 1;
        }
        else if(root.left == null) {
            return right + 1;
        }
        else if(root.right == null) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }
}


// iterative:
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
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(curr.left == null && curr.right == null) {
                    //System.out.println("curr = " + curr.val);
                    return ++result;
                }
                else if(curr.left == null) {
                    queue.offer(curr.right);
                }
                else if(curr.right == null) {
                    queue.offer(curr.left);
                }
                else {
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
            result++;
        }
        return result;
    }
}

