
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


// method 1: Greedy + Heap
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    /* idea:
    1. sort the array by starting time
    2. minHeap stores rooms with first finish time
    if current interval cannot fits in minHeap.top(), then add a new room in this minHeap
    */
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++) {
            // can use minHeap.peek() as meeting room
            if(intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        return minHeap.size();
    }
}




