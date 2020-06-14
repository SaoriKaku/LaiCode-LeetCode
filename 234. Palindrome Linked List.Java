
/*
Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
*/


/*
method 1: find mid node(use fast slow pointer),
reverse the second part(continue move slow, the new head(originally the last node) is prev), 
compare with first part(head vs. prev)
time complexity: O(n), space complexity: O(1)
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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        /*
        1 2 3 4 5 4 3 2 1
        f.  f   f.  f.  f
                s
        1 2 3 4 4 3 2 1
        f.  f.  f.  f
              s
        */
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // odd or even, slow move to next
        slow = slow.next;
        // reverse the second half
        ListNode prev = null;
        while(slow != null) {
            ListNode nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }
        // compare the first half(head) to the second half(prev = newHead of second part)
        while(prev != null) {
            if(head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }
        return true;
    }
}


/*
method 2: find mid node(use fast slow pointer),
store the second part into a stack, 
compare with first part with stack.pop()
time complexity: O(n), space complexity: O(n)
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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        /*
        1 2 3 4 5 4 3 2 1
        f.  f   f.  f.  f
                s
        1 2 3 4 4 3 2 1
        f.  f.  f.  f
              s
        */
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // odd or even, slow move to next
        slow = slow.next;
        // store the second half to a stack
        Deque<Integer> stack = new ArrayDeque<>();
        while(slow != null) {
            stack.push(slow.val);
            slow = slow.next;
        }
        // compare the first half(head) to the stack.pop()
        while(!stack.isEmpty()) {
            if(head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}


// method 3: recursion
// time complexity: O(n), space complexity: O(n)
class Solution {
    public static ListNode curr;
    public boolean isPalindrome(ListNode head) {
        curr = head;
        return helper(head);
    }
    public boolean helper(ListNode head) {
        if(head == null) {
            return true;
        }
        if(helper(head.next)) {
            if(curr.val == head.val) {
                curr = curr.next;
                return true;
            }
            return false;
        }
        return false;
    }
}

