
/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


// method 1: time complexity: O(nlogn), space complexity: O(1) 
class Solution {
    /*
    idea: 1. sort the array by starting time
          2. traverse and check if [i - 1].finish time < [i].start time
    */
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) {
            return true;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
