
/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Example:
Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/


// method 1: inorder + bst + two pointers
// time complexity: O(n + logn + k), space complexity: O(n)
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        List<Integer> inorder = new ArrayList<>();
        // inorder traverse
        TreeNode curr = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            inorder.add(curr.val);
            curr = curr.right;
        }
        // binary search target
        int left = 0, right = inorder.size() - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(inorder.get(mid) <= target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        // two pointers in sorted array
        for(int i = 0; i < k; i++) {
            if(right >= inorder.size() || left >= 0 && target - inorder.get(left) <= inorder.get(right) - target) {
                result.add(inorder.get(left--));
            }
            else {
                result.add(inorder.get(right++));
            }
        }
        return result;
    }
}


// method 2: bst
// time complexity: O(logn + k * logn = height), space complexity: O(logn = height)
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        // 2 < 3 < target = 3.7
        Deque<TreeNode> predStack = new ArrayDeque<>();
        // 4 > target = 3.7
        Deque<TreeNode> succStack = new ArrayDeque<>();
        initializeStack(root, target, predStack, succStack);
        if(!predStack.isEmpty() && !succStack.isEmpty() && predStack.peek() == succStack.peek()) {
            getPredecessor(predStack);
        }
        while(k-- > 0) {
            if(predStack.isEmpty()) {
                result.add(getSuccessor(succStack));
            }
            else if(succStack.isEmpty()) {
                result.add(getPredecessor(predStack));
            }
            else {
                if(succStack.peek().val - target < target - predStack.peek().val) {
                    result.add(getSuccessor(succStack));
                }
                else {
                    result.add(getPredecessor(predStack));
                }
            }
        }
        return result;
    }
    // initialize stack
    public void initializeStack(TreeNode root, double target, Deque<TreeNode> predStack, Deque<TreeNode> succStack) {
        while(root != null) {
            if(root.val == target) {
                predStack.push(root);
                succStack.push(root);
                break;
            }
            else if(root.val < target) {
                predStack.push(root);
                //System.out.println("pred" + root.val);
                root = root.right;
            }
            else {
                succStack.push(root);
                //System.out.println("succ" + root.val);
                root = root.left;
            }
        }
    }
    // getPredecessor(N), which returns the next smaller node to N.
    public int getPredecessor(Deque<TreeNode> predStack) {
        TreeNode curr = predStack.pop();
        int value = curr.val;
        // find next.val < curr.val, go left
        // find max in left subtree, push all right node in left subtree
        curr = curr.left;
        while(curr != null) {
            predStack.push(curr);
            curr = curr.right;
        }
        return value;
    }
    // getSuccessor(N), which returns the next larger node to N.
    public int getSuccessor(Deque<TreeNode> succStack) {
        TreeNode curr = succStack.pop();
        int value = curr.val;
        // find next.val > curr.val, go right
        // find min in right subtree, push all left node in right subtree
        curr = curr.right;
        while(curr != null) {
            succStack.push(curr);
            curr = curr.left;
        }
        return value;
    }
}

