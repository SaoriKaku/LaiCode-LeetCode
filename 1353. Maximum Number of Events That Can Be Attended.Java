
/*
Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.

Example 1:
Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

Example 2:
Input: events= [[1,2],[2,3],[3,4],[1,2]]
Output: 4

Example 3:
Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
Output: 4

Example 4:
Input: events = [[1,100000]]
Output: 1

Example 5:
Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
Output: 7
 
Constraints:
1 <= events.length <= 105
events[i].length == 2
1 <= startDayi <= endDayi <= 105
*/

// method1: minHeap
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        // minHeap: poll earlist 
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        int result = 0, day = 0, i = 0;
        while(i < events.length || !minHeap.isEmpty()) {
            // 开始日期小于已经活动日期，就放到heap里面去
            if(minHeap.isEmpty()) {
                day = events[i][0];
            }
            while(i < events.length && events[i][0] <= day) {
                minHeap.offer(events[i][1]);
                i++;
            }
            minHeap.poll();
            result++;
            day++;
            // 把结束日期早于已经活动日期，从heap中拿走
            while(!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
        }
        return result;
    }
}
