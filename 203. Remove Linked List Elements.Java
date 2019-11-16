
/*
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
*/


// method 1: time complexity: O(n), space complexity: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while(curr.next != null) {
            if(curr.next.val == val) {
                ListNode temp = curr.next;
                curr.next = temp.next;
                temp.next = null;
            }
            else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}



