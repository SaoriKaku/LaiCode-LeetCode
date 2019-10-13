
/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currList = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                currList.add(currNode.val);
                if(currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if(currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
            result.add(currList);
        }
        return result;
    }
}




