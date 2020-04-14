
/*
Given a complete binary tree, count the number of nodes.

Note:
Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:
Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
*/


// method 1: for general tree
// time complexity: O(n), space complexity: O(logn)
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
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return 1 + left + right;
    }
}


// method 2: root.val corresponds to its position, binary search for leaf nodes
// time complexity: O(logn^2), space complexity: O(logn)
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
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int height = getHeight(root);
        int left = (int)Math.pow(2, height - 1);
        int right = (int)Math.pow(2, height) - 1;
        //System.out.println("left = " + left + ", right = " + right);
        while(left < right) {
            int mid = left + (right - left + 1) / 2;
            //System.out.println(findNode(root, mid));
            if(findNode(root, mid)) {
                left = mid;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
    public int getHeight(TreeNode root) {
        int height = 1;
        while(root.left != null) {
            root = root.left;
            height++;
        }
        return height;
    }
    public boolean findNode(TreeNode root, int target) {
        List<Integer> path = new ArrayList<>();
        while(target > 0) {
            path.add(target);
            target /= 2;
        }
        //System.out.println("path: " + path);
        for(int i = path.size() - 1; i >= 0; i--) {
            if(root == null) {
                return false;
            }
            if(i == 0) {
                return true;
            }
            else if(path.get(i) * 2 == path.get(i-1)) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        return true;
    }
}


// method 3: recursion, 
// left subtree is uncomplete, right subtree count = pow(2, right subtree height)
// right subtree is uncomplete, left subtree count = pow(2, left subtree height)
// time complexity: O(logn ^ 2), space complexity: O(logn)
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
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftLeftHeight = getLeftHeight(root.left);
        int leftRightHeight = getRightHeight(root.left);
        int rightLeftHeight = getLeftHeight(root.right);
        // left subtree is complete
        if(leftLeftHeight == leftRightHeight) {
            return (int)Math.pow(2, leftLeftHeight) + countNodes(root.right);
        }
        else { // left subtree is uncomplete, right subtree is complete
            return (int)Math.pow(2, rightLeftHeight) + countNodes(root.left);
        }
    }
    public int getLeftHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int height = 1;
        while(root.left != null) {
            height++;
            root = root.left;
        }
        return height;
    }
    public int getRightHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int height = 1;
        while(root.right != null) {
            height++;
            root = root.right;
        }
        return height;
    }
}


// method 4: iteration 
// add curr node += 1
// left subtree is uncomplete, right subtree count = pow(2, right subtree height) - 1
// right subtree is uncomplete, left subtree count = pow(2, left subtree height) - 1
// time complexity: O(logn ^ 2), space complexity: O(logn)
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        TreeNode curr = root;
        int count = 0;
        while(curr != null) {
            count += 1;
            int leftNodeLeftHeight = findLeftHeight(curr.left);
            int leftNoderightHeight = findRightHeight(curr.left);
            int rightNodeLeftHeight = findLeftHeight(curr.right);
            if(leftNodeLeftHeight == leftNoderightHeight) {
                count += Math.pow(2, leftNodeLeftHeight) - 1;
                curr = curr.right;
            }
            else {
                count += Math.pow(2, rightNodeLeftHeight) - 1;
                curr = curr.left;
            }
        }
        return count;
    } 
    public int findLeftHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            root = root.left;
            height++;
        }
        return height;
    }
    public int findRightHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            root = root.right;
            height++;
        }
        return height;
    }
}

