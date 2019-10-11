
/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// m list, each length is n
// time complexity: O(m * n)
// space complexity: O(m)
class Solution {
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while(curr != null) {
            int currVal = Integer.MAX_VALUE;
            int currIndex = -1;
            for(int i = 0; i < lists.length; i++) {
                if(lists[i] != null && lists[i].val < currVal) {
                    currIndex = i;
                    currVal = lists[i].val;
                }
            }
            if(currIndex == -1) {
                break;
            }
            curr.next = lists[currIndex];
            curr = curr.next;
            lists[currIndex] = lists[currIndex].next;
        }
        return dummy.next;
    }
}



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public class MyComparator implements Comparator<ListNode> {
        public int compare(ListNode one, ListNode two) {
            if(one.val == two.val) {
                return 0;
            }
            return one.val < two.val ? -1 : 1;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode currNode = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, new MyComparator());
        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }
        while(!minHeap.isEmpty()) {
            ListNode pollNode = minHeap.poll();
            ListNode nextNode = pollNode.next;
            if(nextNode != null) {
                minHeap.offer(nextNode);
            }
            currNode.next = pollNode;
            currNode = currNode.next;
        }
        return dummy.next;
    }
}











