
/*
You have some sticks with positive integer lengths.
You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
Return the minimum cost of connecting all the given sticks into one stick in this way.

Example 1:
Input: sticks = [2,4,3]
Output: 14

Example 2:
Input: sticks = [1,8,3,5]
Output: 30

Constraints:
1 <= sticks.length <= 10^4
1 <= sticks[i] <= 10^4
*/


// method 1: Heap
// time complexity: O(n + nlogn), space complexity: O(n)
class Solution {
    public int connectSticks(int[] sticks) {
        if(sticks == null || sticks.length <= 1) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int curr: sticks) {
            minHeap.offer(curr);
        }
        int result = 0;
        while(!minHeap.isEmpty()) {
            int sum = minHeap.poll() + minHeap.poll();
            result += sum;
            if(!minHeap.isEmpty()) {
                minHeap.offer(sum);
            }
        }
        return result;
    }
}


