
/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/


// method 1: find midNode + reverse linkedlist
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
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        ListNode midNode = getMidNode(head);
        // System.out.println("midNode = " + midNode.val);
        ListNode curr2 = reverseList(midNode);
        // System.out.println("curr2 = " + curr2.val);
        ListNode curr1 = head.next;
        ListNode curr = head;
        // 1 -> 2 -> 3
        // 5 -> 4 -> 3
        // 1 -> 5 -> 2 -> 4 -> 3
        // 1 -> 2 
        // 4 -> 3
        // 1 -> 4 -> 2 -> 3
        int i = 1;
        while(curr1 != midNode || curr2 != midNode) {
            if(i % 2 == 0) {
                curr.next = curr1;
                curr1 = curr1.next;
            }
            else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
            i++;
        }
        curr.next = midNode;
    }
    public ListNode getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast.next == null ? slow : slow.next;
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }
}


