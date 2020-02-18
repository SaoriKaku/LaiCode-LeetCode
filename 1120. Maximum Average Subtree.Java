
/*
Given the root of a binary tree, find the maximum average value of any subtree of that tree.
(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

Example 1:
Input: [5,6,1]
Output: 6.00000
Explanation: 
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
 
Note:
The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
*/


// method 1: postorder
// time complexity: O(n), space complexity: O(height of tree)
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
    public class Node {
        int count;
        int sum;
        public Node(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }
    public double maximumAverageSubtree(TreeNode root) {
        if(root == null) {
            return 0.0;
        }
        double[] result = new double[1];
        Node node = maximumAverageSubtreeHelper(root, result);
        return result[0];
    }
    public Node maximumAverageSubtreeHelper(TreeNode root, double[] result) {
        if(root == null) {
            return new Node(0, 0);
        }
        Node left = maximumAverageSubtreeHelper(root.left, result);
        Node right = maximumAverageSubtreeHelper(root.right, result);
        int count = left.count + right.count + 1;
        int sum = left.sum + right.sum + root.val;
        double average = sum * 1.0 / count;
        if(average > result[0]) {
            result[0] = average;
        }
        return new Node(count, sum);
    }
}
