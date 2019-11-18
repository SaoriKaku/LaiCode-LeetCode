
/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.

Follow up:

Could you solve it without looking up any of the node's values?
while (x.parent != null && x == x.parent.right) 
	x = x.parent;
return x.parent;
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
class Solution {
    public Node inorderSuccessor(Node x) {
        if(x == null) {
            return null;
        }
        // if x has right child, find the most left child of this right child
        if(x.right != null) {
            Node curr = x.right;
            while(curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
        // otherwise, find the parent when parent.val > x.val
        Node curr = x.parent;
        while(curr != null && curr.val < x.val) {
            curr = curr.parent;
        }
        return curr;
    }
}


