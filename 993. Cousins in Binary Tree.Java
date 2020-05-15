
/*
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.

Example 1:
Input: root = [1,2,3,4], x = 4, y = 3
Output: false

Example 2:
Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true

Example 3:
Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 
Note:
The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
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

// method 1: bfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            for(int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(currNode.left != null && currNode.right != null) {
                    int leftVal = currNode.left.val;
                    int rightVal = currNode.right.val;
                    if(leftVal == x && rightVal == y || leftVal == y && rightVal == x) {
                        return false;
                    }
                }
                if(currNode.left != null) {
                    queue.offer(currNode.left);
                    int leftVal = currNode.left.val;
                    if(leftVal == x || leftVal == y) {
                        count++;
                    }
                }
                if(currNode.right != null) {
                    queue.offer(currNode.right);
                    int rightVal = currNode.right.val;
                    if(rightVal == x || rightVal == y) {
                        count++;
                    }
                }
            }
            if(count == 2) return true;
        }
        return false;
    }
}




