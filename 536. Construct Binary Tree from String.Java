
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
    public int index = 0;
    public TreeNode str2tree(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        char[] input = s.toCharArray();
        return str2treeHelper(input);
    }
    public TreeNode str2treeHelper(char[] input) {
        //System.out.println("index = " + index);
        if(index == input.length) {
            return null;
        }
        int left = index;
        StringBuilder number = new StringBuilder();
        while(index < input.length && input[index] != '(' && input[index] != ')') {
            number.append(input[index]);
            index++;
        }
        int value = Integer.valueOf(number + "");
        //System.out.println("value = " + value);
        TreeNode root = new TreeNode(value);
        if(index < input.length && input[index] == '(') {
            index++;
            root.left = str2treeHelper(input);
            index++;
        }
        //System.out.println(input[index] + "" + input[index + 1]);
        if(index < input.length && input[index] == '(') {
            index++;
            root.right = str2treeHelper(input);
            index++;
        }
        return root;
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
        int i = 0;
        char[] input = s.toCharArray();
        TreeNode root = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(i < input.length) {
            if(input[i] == '(') {
                i++;
                continue;
            }
            if(input[i] == ')') {
                TreeNode popNode = stack.pop();
                //System.out.println("popNode = " + popNode.val);
                i++;
                continue;
            }
            
            root = stack.peek();
            StringBuilder number = new StringBuilder();
            while(i < input.length && input[i] != '(' && input[i] != ')') {
                number.append(input[i]);
                i++;
            }
            int value = Integer.valueOf(number.toString());
            TreeNode curr = new TreeNode(value);
            //System.out.println("curr = " + value);
            if(root != null) {
                if(root.left == null) {
                    root.left = curr;
                }
                else {
                    root.right = curr;
                }
            }
            stack.push(curr);
        }
        return stack.pop();
    }
}


