
/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.

Follow up:
Could you do this in one pass?
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode last = dummy;
        for(int i = 0; i < n; i++) {
            last = last.next;
        }
        ListNode prev = dummy;
        while(last.next != null) {
            prev = prev.next;
            last = last.next;
        }
        //System.out.println("prev = " + prev.val + ", last = " + last.val);
        prev.next = prev.next.next;
        return dummy.next;
    }
}





