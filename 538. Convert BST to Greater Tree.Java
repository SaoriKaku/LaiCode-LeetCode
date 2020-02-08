
/*
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
*/


// method 1: recursion inorder
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
    /*
     2        5        2
    / \      / \      / \
   0   3    6   3    2   6
  / \      / \      / \
 -4 1     2   6    -4 4
    */
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        root.right = convertBST(root.right);
        sum += root.val;
        root.val = sum;
        root.left = convertBST(root.left);
        return root;
    }
}


// method 2: iteration inorder
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
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode nextNode = root;
        int sum = 0;
        while(nextNode != null || !stack.isEmpty()) {
            if(nextNode != null) {
                //System.out.println("right: " + nextNode.val);
                stack.push(nextNode);
                nextNode = nextNode.right;
            }
            else {
                nextNode = stack.pop();
                //System.out.println("inorder: " + nextNode.val);
                nextNode.val += sum;
                sum = nextNode.val;
                //System.out.println("after: " + nextNode.val);
                nextNode = nextNode.left;
            }
        }
        return root;
    }
}


// method 3: iteration inorder
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
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int sum = 0;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            sum += curr.val;
            curr.val = sum;
            curr = curr.left;
        }
        return root;
    }
}

