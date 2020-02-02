
/*
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/


// method 1: linear traverse
// time complexity: O(n), space compelxity: O(1)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode newHeadPrev = dummy;
        int i = n - m;
        while(m > 1) {
            newHeadPrev = newHeadPrev.next;
            m--;
        }
        ListNode tail = newHeadPrev.next;
        ListNode prev = newHeadPrev;
        ListNode curr = tail;
        // prev curr
        // 1 -> 2 -> 3 -> 4 -> 5 -> null
        // System.out.println("prev1 = " + newHeadPrev.val);
        // System.out.println("curr1 = " + tail.val);
        while(i >= 0) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
            i--;
        }
        //                prev curr
        // 1 -> 2 <- 3 <- 4    5 -> null
        // System.out.println("prev = " + prev.val);
        // System.out.println("curr = " + curr.val);
        newHeadPrev.next = prev;
        tail.next = curr;
        return dummy.next;
    }
}

