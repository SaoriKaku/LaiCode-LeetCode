
/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/


// method 1: stack + linked list
// time complexity: O(n), space complexity: O(n)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /*
    7 -> 2 -> 4 -> 3
         5 -> 6 -> 4
    7 -> 8 -> 0 -> 7
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        Deque<Integer> stack1 = new ArrayDeque<>();
        while(l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        Deque<Integer> stack2 = new ArrayDeque<>();
        while(l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = null;
        int add = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop();
            int val2 = stack2.isEmpty() ? 0 : stack2.pop();
            int val = val1 + val2 + add;
            add = val >= 10 ? 1 : 0;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            newNode.next = head;
            head = newNode;
        }
        if(add == 1) {
            ListNode newNode = new ListNode(1);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }
}

