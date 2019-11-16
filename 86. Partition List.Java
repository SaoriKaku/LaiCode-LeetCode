
/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return head;
        }
        ListNode dummyBefore = new ListNode(Integer.MIN_VALUE);
        ListNode dummyAfter = new ListNode(x);
        ListNode currBefore = dummyBefore;
        ListNode currAfter = dummyAfter;
        while(head != null) {
            if(head.val < x) {
                currBefore.next = head;
                currBefore = currBefore.next;
            }
            else {
                currAfter.next = head;
                currAfter = currAfter.next;
            }
            head = head.next;
        }
        currBefore.next = dummyAfter.next;
        currAfter.next = null;
        return dummyBefore.next;
    }
}


