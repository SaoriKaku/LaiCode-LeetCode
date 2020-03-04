
/*
Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:
Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:
Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
*/


// method 1: preorder
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
    public int longestConsecutive(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = new int[1];
        longestConsecutiveHelper(root, result);
        return result[0];
    }
    public int longestConsecutiveHelper(TreeNode root, int[] result) {
        if(root == null) {
            return 0;
        }
        int left = longestConsecutiveHelper(root.left, result);
        int right = longestConsecutiveHelper(root.right, result);
        if(root.left != null && root.left.val == root.val + 1) {
            left++;
        }
        else {
            left = 1;
        }
        if(root.right != null && root.right.val == root.val + 1) {
            right++;
        }
        else {
            right = 1;
        }
        int curr = Math.max(left, right);
        result[0] = Math.max(result[0], curr);
        return curr;
    }
}

