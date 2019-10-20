
/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5  
*/


// method 1: recursive
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
    public TreeNode str2tree(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        char[] input = s.toCharArray();
        return str2treeHelper(input, 0, input.length - 1);
    }
    
    public TreeNode str2treeHelper(char[] input, int left, int right) {
        if(input[right] != ')') {
            //System.out.println("left = " + left + " right = " + right);
            int value = Integer.parseInt(String.valueOf(input, left, right - left + 1));
            return new TreeNode(value);
        }
        if(left >= right) {
            return null;
        }
        int bound = left;
        while(bound < input.length && input[bound] != '(' && input[bound] != ')') {
            bound++;
        }
        int value = Integer.parseInt(String.valueOf(input, left, bound - left));
        TreeNode root = new TreeNode(value);
        // the right index of left part
        int mid = findMid(input, left, right);
        //System.out.println("mid = " + mid);
        if(mid == -1) {
            root.left = str2treeHelper(input, bound + 1, right - 1);
            root.right = null;
        }
        else {
            root.left = str2treeHelper(input, bound + 1, mid - 1);
            root.right = str2treeHelper(input, mid + 2, right - 1);
        }
        return root;
    }
    
    public int findMid(char[] input, int left, int right) {
        int balance = 0;
        while(left < right) {
            if(input[left] == '(') {
                balance++;
            }
            else if(input[left] == ')') {
                balance--;
            }
            if(input[left] == ')' && input[left + 1] == '(' && balance == 0) {
                return left;
            }
            left++;
        }
        return -1;
    }
}


// method 2: iterative
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
    public TreeNode str2tree(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        char[] input = s.toCharArray();
        int i = 0;
        TreeNode root = null;
        while(i < input.length) {
            char curr = input[i];
            if(curr == '(') {
                root = stack.peek();
                i++;
            }
            else if(curr == ')') {
                i++;
                stack.pop();
            }
            else {
                int bound = i;
                while(bound < input.length && input[bound] != '(' && input[bound] != ')') {
                    bound++;
                }
                int value = Integer.parseInt(String.valueOf(input, i, bound - i));
                TreeNode currNode = new TreeNode(value);
                if(root != null) {
                    if(root.left == null) {
                        root.left = currNode;
                    }
                    else {
                        root.right = currNode;
                    }
                } 
                stack.push(currNode);
                i = bound;
            }
        }
        return stack.pop();
    }
}

