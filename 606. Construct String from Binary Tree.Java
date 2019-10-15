
/*
You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
*/

// method 1: DFS
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
    public String tree2str(TreeNode t) {
        if(t == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        tree2strHelper(t, result);
        return result.toString();
    }
    public void tree2strHelper(TreeNode t, StringBuilder result) {
        if(t == null) {
            return;
        }
        result.append(t.val);
        if(t.left == null && t.right == null) {
            return;
        }
            result.append("(");
            tree2strHelper(t.left, result);
            result.append(")");
        
        if(t.right != null) {
            result.append("(");
            tree2strHelper(t.right, result);
            result.append(")");
        }
        return;
    }
}


// method 2: BFS 
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
    public String tree2str(TreeNode t) {
        if(t == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(t);
        HashSet<TreeNode> visited = new HashSet<>();
        while(!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            if(visited.contains(curr)) {
                stack.pop();
                result.append(")");
            }
            else {
                visited.add(curr);
                result.append("(" + curr.val);
                if(curr.left == null && curr.right != null) {
                    result.append("()");
                }
                if(curr.right != null) {
                    stack.push(curr.right);
                }
                if(curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }
        return result.toString().substring(1, result.length() - 1);
    }
}



