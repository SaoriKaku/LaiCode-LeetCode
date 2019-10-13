
/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> postorderTraversal(TreeNode root) {
        /*记录一个prev，看prev和curr的关系
        prev = null, prev = curr, curr = curr.left
        perv = curr.parent, prev = curr, curr = curr.left
        prev = curr.left, prev = curr, curr = curr.right;
        prev = curr.right, prev = curr, print curr, go up, curr = stack.pop()
        */
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        
        TreeNode prev = null;
        while(!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            // go down
            if(prev == null || curr == prev.left || curr == prev.right) {
                if(curr.left != null) {
                    stack.push(curr.left);
                }
                else if(curr.right != null) {
                    stack.push(curr.right);
                }
                else {
                    result.add(curr.val);
                    stack.poll();
                }
            }
            else if(prev == curr.left) {
                if(curr.right != null) {
                    stack.push(curr.right);
                }
                else {
                    result.add(curr.val);
                    stack.poll();
                }
            }
            else if(prev == curr.right) {
                result.add(curr.val);
                curr = stack.pop();
            }
            prev = curr;
        }
        return result;
    }
}



