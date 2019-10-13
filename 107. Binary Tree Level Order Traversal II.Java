
/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        levelOrderBottomHelper(root, result, 1);
        return result;
    }
    public void levelOrderBottomHelper(TreeNode root, List<List<Integer>> result, int level) {
        if(root == null) {
            return;
        }
        if(level > result.size()) {
            List<Integer> curr = new ArrayList<>();
            result.add(0, curr);
        }
        levelOrderBottomHelper(root.left, result, level + 1);
        levelOrderBottomHelper(root.right, result, level + 1);
        result.get(result.size() - level).add(root.val);
       
    }
}


