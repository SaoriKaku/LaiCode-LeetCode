
/*
Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:
get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 
Example:
Input: 
["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]
Output:  
[null,null,null,null,2,null,3]

Explanation:
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
 
Constraints:
0 <= index,val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail,  addAtIndex and deleteAtIndex.
*/


// method 1: singly linked list with dummy node
// time complexity: addHead: O(1), others: O(n)
class MyLinkedList {
    class Node {
        Node next;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    private Node dummy;
    private Node tail;
    public MyLinkedList() {
        dummy = new Node(-1);
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node curr = dummy;
        while(index >= 0 && curr != null) {
            curr = curr.next;
            index--;
        }
        if(curr == null) return -1;
        return curr.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node nextNode = dummy.next;
        Node head = new Node(val);
        dummy.next = head;
        head.next = nextNode;
        //printList();
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node prevNode = dummy;
        while(prevNode.next != null) {
            prevNode = prevNode.next;
        }
        prevNode.next = new Node(val);
        //printList();
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    // -1 -> 0 -> 1 -> 2 -> null
    public void addAtIndex(int index, int val) {
        Node prevNode = dummy;
        while(prevNode != null && index > 0) {
            prevNode = prevNode.next;
            index--;
        }
        if(prevNode == null) return;
        Node nextNode = prevNode.next;
        Node currNode = new Node(val);
        prevNode.next = currNode;
        currNode.next = nextNode;
        //printList();
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        Node prevNode = dummy;
        while(prevNode.next != null && index > 0) {
            prevNode = prevNode.next;
            index--;
        }
        if(prevNode.next == null) return;
        Node nextNode = prevNode.next.next;
        Node currNode = prevNode.next;
        prevNode.next = nextNode;
        currNode.next = null;
    }
    public void printList() {
        Node curr = dummy;
        while(curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */




