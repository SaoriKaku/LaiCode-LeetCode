
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
// method 1: top -> bottom
// time complexity: O(nlogn), space complexity: O(logn)
class Solution {
    public ListNode sortList(ListNode head) {
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


// method 2: top -> bottom
// time complexity: O(nlogn), space complexity: O(logn)
/*
4 -> 2 -> 1 -> 3 -> null
          s
                    f
fast.next != null && fast.next.next != null, s = 2
fast != null && fast.next != null, s = 1
*/
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode midNode = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(midNode));
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
        if(head1 != null) {
            curr.next = head1;
        }
        if(head2 != null) {
            curr.next = head2;
        }
        return dummy.next;
    }
}


// method 3: bottom -> top
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    /*
    4 -> 2 -> 1 -> 3, total number = 4, n = log2(4) = 2
    4, 2, 1 -> 3, n = 1
    2->4, 1 -> 3, n = 1
    2->4, 1, 3    n = 1
    2->4 -> 1->3  n = 2
    2->4  1->3    n = 2
    1->2->3->4    n = 2
    */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode curr = head;
        while(curr != null) {
            curr = curr.next;
            len++;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for(int i = 1; i < len; i *= 2) {
            curr = dummy.next;
            ListNode tail = dummy;
            while(curr != null) {
                ListNode leftHead = curr;
                ListNode rightHead = split(leftHead, i);
                curr = split(rightHead, i);
                ListNode[] mergeListHeadTail = merge(leftHead, rightHead);
                tail.next = mergeListHeadTail[0];
                tail = mergeListHeadTail[1];
            }
        }
        return dummy.next;
    }
    // return the head of the rest list
    public ListNode split(ListNode head, int n) {
        while(--n > 0 && head != null) {
            head = head.next;
        }
        ListNode restHead = head == null ? null : head.next;
        if(head != null) {
            head.next = null;
        }
        return restHead;
    }
    // return the head and tail of the merged list
    public ListNode[] merge(ListNode head1, ListNode head2) {
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
        if(head1 != null) {
            curr.next = head1;
        }
        if(head2 != null) {
            curr.next = head2;
        }
        ListNode tail = dummy;
        while(tail.next != null) {
            tail = tail.next;
        }
        return new ListNode[] {dummy.next, tail};
    }
}



