
/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        // top -> bottom
        // time complexity: O(nlogn), space complexity: O(logn)
        if(head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 把两段linked list分开, 一段是head到slow；一段是mid到结束
        ListNode mid = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(mid));
    }
    
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while(head1 != null && head2 != null) {
            if(head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            }
            else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        while(head1 != null) {
            curr.next = head1;
            curr = curr.next;
            head1 = head1.next;
        }
        while(head2 != null) {
            curr.next = head2;
            curr = curr.next;
            head2 = head2.next;
        }
        return dummy.next;
    }
}