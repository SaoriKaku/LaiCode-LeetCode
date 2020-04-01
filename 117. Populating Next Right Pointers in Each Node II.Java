
/*
Given a binary tree
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

Follow up:
You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 
Example 1:
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 
Constraints:
The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node() {}
    public Node(int _val) {
        val = _val;
    }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
// method 1: bfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            Node prevNode = null;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                if(currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if(currNode.right != null) {
                    queue.offer(currNode.right);
                }
                if(prevNode != null) {
                    prevNode.next = currNode;
                }
                prevNode = currNode;
            }
            prevNode.next = null;
        }
        return root;
    }
}


// method 2: bfs
// time complexity: O(n), space complexity: O(1)
class Solution {
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Node leftMostNode = root;
        
        while(leftMostNode != null) {
            Node currNode = leftMostNode;
            Node prevNode = null;
            Node nextLayerHead = null;
            while(currNode != null) {
                if(currNode.left != null) {
                    if(nextLayerHead == null) {
                        nextLayerHead = currNode.left;
                        prevNode = currNode.left;
                    }
                    else {
                        prevNode.next = currNode.left;
                        prevNode = prevNode.next;
                    }
                }
                if(currNode.right != null) {
                    if(nextLayerHead == null) {
                        nextLayerHead = currNode.right;
                        prevNode = currNode.right;
                    }
                    else {
                        prevNode.next = currNode.right;
                        prevNode = prevNode.next;
                    }
                }
                currNode = currNode.next;
            }
            leftMostNode = nextLayerHead;
        }
        return root;
    }
}
