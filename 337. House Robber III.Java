
/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1
Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
*/


// method 1: tree + DP(1D)
// time complexity: O(n), space complexity: O(height of tree)
/*
each node int[2] = {take curr.val, not take curr.val}
curr[0] = left[1] + right[1] + curr.val
curr[1] = max(left[0], left[1]) + max(right[0], right[1]);
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
    public int rob(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }
    public int[] robHelper(TreeNode root) {
        if(root == null) {
            return new int[] {0, 0};
        }
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        int[] curr = new int[2];
        curr[0] = left[1] + right[1] + root.val;
        curr[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return curr;
    }
}




