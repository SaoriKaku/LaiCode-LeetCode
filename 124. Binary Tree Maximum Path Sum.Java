
/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/


// method 1: tree
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
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSumHelper(root, max);
        return max[0];
    }
    public int maxPathSumHelper(TreeNode root, int[] max) {
        if(root == null) {
            return 0;
        }
        int leftSum = maxPathSumHelper(root.left, max);
        int rightSum = maxPathSumHelper(root.right, max);
        leftSum = leftSum < 0 ? 0 : leftSum;
        rightSum = rightSum < 0 ? 0 : rightSum;
        int singlePathSum = Math.max(leftSum + root.val, rightSum + root.val);
        int sum = leftSum + rightSum + root.val;
        max[0] = Math.max(max[0], sum);
        return singlePathSum;
    }
}



