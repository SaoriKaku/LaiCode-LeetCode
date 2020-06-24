
/*
Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9
Output: True
 
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28
Output: False
*/


// method 1: inOrder
// time compleixty: O(n), space complexity: O(n)
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
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int left = 0;
        int right = list.size() - 1;
        while(left < right) {
            int sum = list.get(left) + list.get(right);
            if(sum == k) {
                return true;
            }
            else if(sum < k) {
                left++;
            }
            else {
                right--;
            }
        }
        return false;
    }
    public void inOrder(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}


// method 2: DFS + hashset
// time compleixty: O(n), space complexity: O(n)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        return findTargetHelper(root, k, set);
    }
    public boolean findTargetHelper(TreeNode root, int k, HashSet<Integer> set) {
        if(root == null) {
            return false;
        }
        if(set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTargetHelper(root.left, k, set) || findTargetHelper(root.right, k, set);
    }
}


// method 3: BFS + hashset
// time compleixty: O(n), space complexity: O(n)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if(set.contains(k - curr.val)) {
                return true;
            }
            set.add(curr.val);
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null) {
                stack.push(curr.left);
            }
        }
        return false;
    }
}


// method 4: two pointer, one from smallest, one from biggest
// time complexity: O(n), space complexity: O(height of tree)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        Deque<TreeNode> leftStack = new ArrayDeque<>();
        Deque<TreeNode> rightStack = new ArrayDeque<>();
        TreeNode curr = root;
        while(curr != null) {
            leftStack.push(curr);
            curr = curr.left;
        }
        curr = root;
        while(curr != null) {
            rightStack.push(curr);
            curr = curr.right;
        }
        while(!leftStack.isEmpty() && !rightStack.isEmpty() && leftStack.peek() != rightStack.peek()) {
            TreeNode leftStackNode = leftStack.peek();
            TreeNode rightStackNode = rightStack.peek();
            int sum = leftStackNode.val + rightStackNode.val;
            //System.out.println("left = " + leftStackNode.val + ", right = " + rightStackNode.val);
            //System.out.println("sum = " + sum);
            if(sum == k) {
                return true;
            }
            else if(sum < k) { // leftStack.push();
                curr = leftStack.pop().right;
                while(curr != null) {
                    leftStack.push(curr);
                    curr = curr.left;
                }
            }
            else {
                curr = rightStack.pop().left;
                while(curr != null) {
                    rightStack.push(curr);
                    curr = curr.right;
                }
            }
            //System.out.println("left = " + leftStack.peek().val + ", right = " + rightStack.peek().val);
        }
        return false;
    }
}
