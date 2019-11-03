
/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Example 1:

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 
Note: You must return the copy of the given head as a reference to the cloned list.
*/


/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/


// method 1: one iteration
// time compleixty: O(n), space compleixty: O(n)
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        // HashMap<original Node, copy Node>
        HashMap<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val, null, null);
        //System.out.println("head.val: " + head.val);
        map.put(head, newHead);
        Node curr = newHead;
        /*
                 1 -> 2 -> 3
                 head
                 1 -> 2 -> 3
                 curr
        */
        while(head != null) {
            Node nextNode = null;
            if(head.next != null) {
                if(map.containsKey(head.next)) {
                    nextNode = map.get(head.next);
                }
                else {
                    nextNode = new Node(head.next.val, null, null);
                    map.put(head.next, nextNode);
                }
            }
            Node randomNode = null;
            if(head.random != null) {
                if(map.containsKey(head.random)) {
                    randomNode = map.get(head.random);
                }
                else {
                    randomNode = new Node(head.random.val, null, null);
                    map.put(head.random, randomNode);
                }
            }
            curr.next = nextNode;
            curr.random = randomNode;
            curr = curr.next;
            head = head.next;
        }
        return newHead;
    }
}




