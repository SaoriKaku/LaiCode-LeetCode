
/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
 
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 
Note: All the values of tree nodes are in the range of [-1e7, 1e7].
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
    public int[] longestConsecutiveHelper(TreeNode root, int[] result) {
        if(root == null) {
            return new int[] {0, 0};
        }
        // int[]: int[0] increase; int[1] decrease;
        int[] left = longestConsecutiveHelper(root.left, result);
        int[] right = longestConsecutiveHelper(root.right, result);
        left[0] = root.left != null && root.left.val + 1 == root.val ? left[0] + 1 : 1;
        left[1] = root.left != null && root.left.val == root.val + 1 ? left[1] + 1 : 1;
        right[0] = root.right != null && root.right.val + 1 == root.val ? right[0] + 1 : 1;
        right[1] = root.right != null && root.right.val == root.val + 1 ? right[1] + 1 : 1;
        int incre = Math.max(left[0], right[0]);
        int decre = Math.max(left[1], right[1]);
        result[0] = Math.max(result[0], incre);
        result[0] = Math.max(result[0], decre);
        result[0] = Math.max(result[0], left[0] + right[1] - 1);
        result[0] = Math.max(result[0], left[1] + right[0] - 1);
        return new int[] {incre, decre};     
    }
}




