
/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
*/


// method 1: iterative: inorder
// time complexity: O(height + k), space complexity: O(height + k)
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
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode nextNode = root;
        while(nextNode != null || !stack.isEmpty()) {
            if(nextNode == null) {
                nextNode = stack.pop();
                k--;
                if(k == 0) {
                    return nextNode.val;
                }
                nextNode = nextNode.right;
            }
            else {
                stack.push(nextNode);
                nextNode = nextNode.left;
            }
        }
        return root.val;
    }
}


// method 2: pptimized method 1, iterative: inorder
// time complexity: O(height + k), space complexity: O(height + k)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if(k == 0) {
                return root.val;
            }
            root = root.right;
        }
    }
}

