
/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5

Note:
Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/


// method 1: linked list
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
    /* dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
       prev     tail
    */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, nextNode = prev.next;
        while(prev != null) {
            prev = reverse(prev, k);
        }
        return dummy.next;
    }
    // return tail, tail is prev of next round
    public ListNode reverse(ListNode prev, int k) {
        int i = 0;
        ListNode tail = prev.next, nextHead = tail;
        while(nextHead != null && i < k) {
            nextHead = nextHead.next;
            i++;
        }
        if(nextHead == null && i != k) {
            return null;
        }
        ListNode curr = tail.next;
        while(curr != nextHead) {
            ListNode nextNode = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            tail.next = nextNode;
            curr = nextNode;
        }
        return tail;  
    }
}



