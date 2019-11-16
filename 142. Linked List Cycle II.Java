
/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Follow-up:
Can you solve it without using extra space?
*/


// idea: fast(moves two steps) == slow(moves one step), mean there is a loop; fast == slow(moves one step), return fast or slow.
// time complexity: O(n), space complexity: (1)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        boolean hasLoop = false;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                hasLoop = true;
                break;
            }
        }
        if(hasLoop) {
            fast = head;
            while(fast != slow) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
}

