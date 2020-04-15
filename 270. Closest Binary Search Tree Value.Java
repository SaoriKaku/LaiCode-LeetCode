
/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Example:
Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
*/


// method 1: BST
// time complexity: O(height of tree), space complexity: O(1)
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
    public int closestValue(TreeNode root, double target) {
        TreeNode curr = root;
        int min = root.val, max = root.val;
        while(curr != null) {
            if(curr.val > target) {
                max = curr.val;
                curr = curr.left;
            }
            else {
                min = curr.val;
                curr = curr.right;
            }
        }
        if(Math.abs(max - target) < Math.abs(target - min)) {
            return max;
        }
        return min;
    }
}


