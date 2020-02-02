
/*
Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.

Example 1:
Input: head = [3,4,1], insertVal = 2
Output: [3,4,1,2]
Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

Example 2:
Input: head = [], insertVal = 1
Output: [1]
Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.

Example 3:
Input: head = [1], insertVal = 0
Output: [1,0]
 
Constraints:
0 <= Number of Nodes <= 5 * 10^4
-10^6 <= Node.val <= 10^6
-10^6 <= insertVal <= 10^6
*/


// method 1: time complexity: O(n), space compelxity: O(1)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if(head == null) {
            newNode.next = newNode;
            return newNode;
        }
        if(head.next == head) {
            newNode.next = head;
            head.next = newNode;
            return head;
        }
        Node curr = head;
        Node nextNode = head.next;
        Node maxNode = head;
        while(curr.val > insertVal || nextNode.val < insertVal) {
            curr = nextNode;
            nextNode = nextNode.next;
            if(curr.val >= maxNode.val) {
                maxNode = curr;
            }
            if(curr == head) {
                break;
            }
        }
        // System.out.println("curr = " + curr.val);
        // System.out.println("nextNode = " + nextNode.val);
        if(curr.val <= insertVal && nextNode.val >= insertVal) {
            //curr <= insertVal <= nextNode
            curr.next = newNode;
            newNode.next = nextNode;
        }
        else {
            // curr == minNode
            curr = maxNode.next;
            maxNode.next = newNode;
            newNode.next = curr;
        }
        return head;
    }
}
