
/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 
Note:
next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
*/


// method 1: in-order traverse, turn a tree to be stored in a sorted list
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    public List<Integer> list;
    public int currIndex;
    public TreeNode root;

    public BSTIterator(TreeNode root) {
        this.root = root;
        currIndex = 0;
        list = new ArrayList<>();
        dfs(root, list);
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
    
    /** @return the next smallest number */
    public int next() {
        return list.get(currIndex++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return currIndex < list.size();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */


