
/*
Given a binary search tree, return a balanced binary search tree with the same node values.
A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
If there is more than one answer, return any of them.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 
Constraints:
The number of nodes in the tree is between 1 and 10^4.
The tree nodes will have distinct values between 1 and 10^5.
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

// method 1: tree inorder + construct tree from array
// time complexity: O(n), space complexity: O(n)
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        if(root == null) {
            return root;
        }
        List<Integer> values = new ArrayList<>();
        flattenTree(root, values);
        //System.out.println(values);
        return buildTree(values, 0, values.size() - 1);
    }
    public void flattenTree(TreeNode root, List<Integer> values) {
        if(root == null) {
            return;
        }
        flattenTree(root.left, values);
        values.add(root.val);
        flattenTree(root.right, values);
    }
    public TreeNode buildTree(List<Integer> values, int left, int right) {
        if(left > right) {
            return null;
        }
        if(left == right) {
            return new TreeNode(values.get(left));
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = buildTree(values, left, mid - 1);
        root.right = buildTree(values, mid + 1, right);
        return root;
    }
}


