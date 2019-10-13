
/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
*/


// method 1: iteratively
// time complexity: O(n), space complexity: O(n)
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode nextNode = root;
        while(nextNode != null || !stack.isEmpty()) {
            if(nextNode != null) {
                stack.push(nextNode);
                nextNode = nextNode.left;
            }
            else {
                nextNode = stack.pop();
               // System.out.println(stack.size() + " " + nextNode);
                result.add(nextNode.val);
                nextNode = nextNode.right;
            }
        }
        return result;
    }
}