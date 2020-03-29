
/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted linked list: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// method 1: inorder
// time complexity: O(nlogn), space complexity: O(logn)
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        } 
        return helper(head, null);
    }
    public TreeNode helper(ListNode firstNode, ListNode lastNode) {
        ListNode slow = firstNode;
        ListNode fast = firstNode;
        if(firstNode == lastNode) {
            return null;
        }
        while(fast != lastNode && fast.next != lastNode){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(firstNode, slow);
        root.right = helper(slow.next, lastNode);
        return root;
    }
}




