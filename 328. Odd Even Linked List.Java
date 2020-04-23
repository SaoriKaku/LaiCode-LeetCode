
/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL

Example 2:
Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
*/


// method 1: Linked List
// time complexity: O(n), space complexity: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddNode = null, evenNode = null;
        ListNode curr = head; // always stand on odd node, will be the last odd node
        ListNode evenHead = head.next; // to connect the last odd node
        while(curr.next != null && curr.next.next != null) {
            oddNode = curr;
            evenNode = curr.next;
            curr = curr.next.next;
            oddNode.next = curr;
            evenNode.next = curr.next;
        }
        if(curr.next != null) { // last node is even = curr.next
            oddNode.next = curr;
            evenNode.next = curr.next;
        }
        else { // last node is odd
            oddNode.next = curr;
        }
        curr.next = evenHead;
        return head;
    }
}



