
/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        pathSumHelper(root, sum, curr, result);
        return result;
    }
    public void pathSumHelper(TreeNode root, int sum, List<Integer> curr, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null) {
            if(sum - root.val == 0) { 
                curr.add(root.val);
                result.add(new ArrayList<>(curr));
                curr.remove(curr.size() - 1);
                return;
            }
        }
        curr.add(root.val);
        pathSumHelper(root.left, sum - root.val, curr, result);
        pathSumHelper(root.right, sum - root.val, curr, result);
        curr.remove(curr.size() - 1);
    }
}


// method 1 (another version)
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        List<Integer> curr = new ArrayList<>();
        pathSumHelper(root, sum, curr, result);
        return result;
    }
    public void pathSumHelper(TreeNode root, int sum, List<Integer> curr, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        curr.add(root.val);
        if(root.left == null && root.right == null) {
            if(sum - root.val == 0) { 
                result.add(new ArrayList<>(curr));
            }
        }
        pathSumHelper(root.left, sum - root.val, curr, result);
        pathSumHelper(root.right, sum - root.val, curr, result);
        curr.remove(curr.size() - 1);
    }
}




