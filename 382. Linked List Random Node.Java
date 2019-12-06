
/*
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
*/


// method 1: reservior sampling, 
// find the list length, generate random index, find the node.value with that random index
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
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    private int listLength;
    private ListNode head;
    public Solution(ListNode head) {
        this.head = head;
        listLength = 0;
        ListNode curr = head;
        while(curr != null) {
            listLength++;
            curr = curr.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(listLength);
        ListNode curr = head;
        while(index > 0) {
            curr = curr.next;
            index--;
        }
        return curr.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */


// method 2: reservior sampling, 
// generate random index, if random index == 0, choose the last node.value,
// because the probability that the last node.value is chosen == 1/(number of list so far)
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
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    private ListNode head;
    private Random random;
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode curr = head;
        int count = 0; // count the length of list
        int index = 0; // the random index
        int result = head.val;
        while(curr != null) {
            count++;
            index = random.nextInt(count);
            /* if random index == 0, probability = 1/(the length of list)
               it is the probability that newest element was chosen
               0  1  2
               0: 1 * 1/2 * 2/3 = 1/3
               1: 1/2 * 2/3 = 1/3
               2: 1/3
            */
            if(index == 0) {
                result = curr.val;
            }
            curr = curr.next;
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

