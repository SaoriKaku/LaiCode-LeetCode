
/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
An example is the root-to-leaf path 1->2->3 which represents the number 123.
Find the total sum of all root-to-leaf numbers.
Note: A leaf is a node with no children.

Example:
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
*/


// method 1: recursion
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
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = new int[1];
        sumNumbersHelper(root, 0, result);
        return result[0];
    }
    public void sumNumbersHelper(TreeNode root, int path, int[] result) {
        if(root == null) {
            return;
        }
        path = 10 * path + root.val;
        if(root.left == null && root.right == null) {
            result[0] += path;
        }
        sumNumbersHelper(root.left, path, result);
        sumNumbersHelper(root.right, path, result);
        path = (path - root.val) / 10;
    }
}


// method 2: iteration
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int result = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> paths = new ArrayDeque<>();
        stack.push(root);
        paths.push(root.val);
        while(!stack.isEmpty()) {
            TreeNode currNode = stack.pop();
            int path = paths.pop();
            if(currNode.left == null && currNode.right == null) {
                //System.out.println("path = " + path);
                result += path;
                continue;
            }
            if(currNode.left != null) {
                stack.push(currNode.left);
                paths.push(10 * path + currNode.left.val);
            }
            if(currNode.right != null) {
                stack.push(currNode.right);
                paths.push(10 * path + currNode.right.val);
            }
        }
        return result;
    }
}



