
/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
Return the linked list sorted as well.

Example 1:
Input: 1->2->3->3->4->4->5
Output: 1->2->5

Example 2:
Input: 1->1->1->2->3
Output: 2->3
*/


/*
    dummy->1->2->3->3->4->4->5
        p  c
    dummy->1->2->3->3->4->4->5
        p     c
    dummy->1->2->3->3->4->4->5
           p  c
    dummy->1->2->3->3->4->4->5
           p     c
    dummy->1->2->3->3->4->4->5
              p     c
    dummy->1->2->3->3->4->4->5
              p        c
    dummy->1->2->4->4->5
              p  c
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr != null) {
            if(prev.next == curr) {
                curr = curr.next;
            }
            else if(prev.next.val != curr.val) {
                prev = prev.next;
            }
            else { // prev.next.val = curr.val
                while(curr != null && curr.val == prev.next.val) {
                    curr = curr.next;
                }
                if(curr == null) {
                    prev.next = null;
                    break;
                }
                prev.next = curr;
            }
        }
        return dummy.next;
    }
}


