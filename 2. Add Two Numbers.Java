
/*
ou are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        boolean add = false;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while(l1 != null && l2 != null) {
            int currVal = l1.val + l2.val;
            if(add) {
                currVal += 1;
                add = false;
            }
            if(currVal >= 10) {
                currVal -= 10;
                add = true;
            }
            ListNode newNode = new ListNode(currVal);
            curr.next = newNode;
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while(l1 != null) {
            int currVal = l1.val;
            if(add) {
                currVal += 1;
                add = false;
            }
            if(currVal >= 10) {
                currVal -= 10;
                add = true;
            }
            ListNode newNode = new ListNode(currVal);
            curr.next = newNode;
            curr = curr.next;
            l1 = l1.next;
        }
        
        while (l2 != null) {
            int currVal = l2.val;
            if(add) {
                currVal += 1;
                add = false;
            }
            if(currVal >= 10) {
                currVal -= 10;
                add = true;
            }
            ListNode newNode = new ListNode(currVal);
            curr.next = newNode;
            curr = curr.next;
            l2 = l2.next;
        }
        
        if(add) {
            ListNode newNode = new ListNode(1);
            add = false;
            curr.next = newNode;
            newNode.next = null;
        }
        return dummy.next;
    }
}


// method 2
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int add = 0;
        while(l1 != null || l2 != null) {
            int a = (l1 == null) ? 0 : l1.val;
            int b = (l2 == null) ? 0 : l2.val;
            int currVal = a + b + add;
            add = currVal / 10;
            curr.next = new ListNode(currVal % 10);
            curr = curr.next;
            if(l1 != null) {
                 l1 = l1.next;
            }
            if(l2 != null) {
                 l2 = l2.next;
            }
        }
        
        if(add > 0) {
            ListNode newNode = new ListNode(add);
            curr.next = newNode;
            newNode.next = null;
        }
        return dummy.next;
    }
}













