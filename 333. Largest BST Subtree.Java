
/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:
Input: [10,5,15,1,8,null,7]

   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
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
    public class Node {
        boolean isBST;
        int count;
        int min;
        int max;
        public Node(boolean isBST, int count, int min, int max) {
            this.isBST = isBST;
            this.count = count;
            this.min = min;
            this.max = max;
        }
    }
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = new int[1];
        result[0] = 1;
        largestBSTSubtreeHelper(root, result);
        return result[0];
    }
    public Node largestBSTSubtreeHelper(TreeNode root, int[] result) {
        if(root == null) {
            return new Node(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Node left = largestBSTSubtreeHelper(root.left, result);
        Node right = largestBSTSubtreeHelper(root.right, result);
        int count = left.count + right.count + 1;
        int min = Math.min(root.val, left.min);
        int max = Math.max(root.val, right.max);
        //System.out.println(root.val + ", " + left.isBST + ", " + right.isBST + ", " + left.max + ", " + right.min);
        if(left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            //System.out.println(root.val);
            result[0] = Math.max(result[0], count);
            return new Node(true, count, min, max);
        }
        return new Node(false, count, min, max);
    }
}




