
/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

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
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 
Constraints:
The number of nodes in the given tree is less than 4096.
-1000 <= node.val <= 1000
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
// time complexity: O(n), space comeplxity: O(n)
class Solution {
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Node prevNode = null;
            for(int i = 0; i < size; i++) {
                Node currNode = queue.poll();
                if(prevNode != null) {
                    prevNode.next = currNode;
                }
                if(currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if(currNode.right != null) {
                    queue.offer(currNode.right);
                }
                prevNode = currNode;
            }
            prevNode.next = null;
        }
        return root;
    }
}


// method 2: bfs
// time complexity: O(n), space comeplxity: O(1)
class Solution {
    /* idea: currNode on the upper layer, 
             connect node.next in the currNode's lower layer
       1
     2   3
    4 5 6 7
    */
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }
        Node leftMostNode = root;
        while(leftMostNode.left != null) {
            Node currNode = leftMostNode;
            while(currNode != null) {
                currNode.left.next = currNode.right;
                if(currNode.next != null) {
                    currNode.right.next = currNode.next.left;
                }
                else {
                    currNode.right.next = null;
                }
                currNode = currNode.next;
            }   
            leftMostNode = leftMostNode.left;   
        }
        return root;
    }
}

