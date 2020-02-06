
/*
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3
*/


// method 1: back tracking + StringBuilder
// time complexity: O(n), space complexity: O(height)
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        StringBuilder curr = new StringBuilder();
        binaryTreePathsHelper(root, curr, result);
        return result;
    }
    public void binaryTreePathsHelper(TreeNode root, StringBuilder curr, List<String> result) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            curr.append(root.val);
            result.add(curr.toString());
            curr.delete(findLastNumber(curr) + 1, curr.length());
            return;
        }
        curr.append(root.val + "->");
        binaryTreePathsHelper(root.left, curr, result);
        binaryTreePathsHelper(root.right, curr, result);
        curr.delete(curr.length() - 2, curr.length());
        curr.delete(findLastNumber(curr) + 1, curr.length());
    }
    public int findLastNumber(StringBuilder curr) {
        int index = curr.length() - 1;
        while(index >= 0) {
            if(curr.charAt(index) == '>') {
                return index;
            }
            index--;
        }
        return -1;
    }
}


// method 2: back tracking + String
// time complexity: O(n), space complexity: O(height)
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        binaryTreePathsHelper(root, "", result);
        return result;
    }
    public void binaryTreePathsHelper(TreeNode root, String curr, List<String> result) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            curr += root.val;
            result.add(curr);
            return;
        }
        curr += root.val + "->";
        binaryTreePathsHelper(root.left, curr, result);
        binaryTreePathsHelper(root.right, curr, result);
    }
}


// method 3: preorder + stack
// time complexity: O(n), space complexity: O(height)
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<String> paths = new ArrayDeque<>();
        stack.push(root);
        paths.push(root.val + "");
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            String path = paths.pop();
            if(curr.left == null && curr.right == null) {
                result.add(path);
                continue;
            }
            if(curr.left != null) {
                stack.push(curr.left);
                paths.push(path + "->" + curr.left.val);
            }
            if(curr.right != null) {
                stack.push(curr.right);
                paths.push(path + "->" + curr.right.val);
            }
        }
        return result;
    }
}


