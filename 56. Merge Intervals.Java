
/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:
Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


// method 1: interval
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int[][] merge(int[][] intervals) {
        /*
        int row = intervals.length;
        int col = intervals[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(intervals[i][j] + " ");
            }
            System.out.println();
        }
        */
        
        if(intervals == null || intervals.length == 0) {
            return new int[][]{};
        }
        
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return 0;
                }
                return a[0] < b[0] ? -1 : 1;
            }
        });
      
        List<int[]> result = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }
            else {
                int[] curr = {start, end};
                result.add(curr);
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        result.add(new int[] {start, end});
        return result.toArray(new int[result.size()][]);
    }
}


// method 2: interval
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, (i1, i2) -> (i1[0] - i2[0]));
        int[] prev = null;
        List<int[]> result = new ArrayList<>();
        for(int[] curr: intervals) {
            if(prev == null || prev[1] < curr[0]) {
                result.add(curr);
                prev = curr;
            }
            else {
                prev[0] = Math.min(prev[0], curr[0]);
                prev[1] = Math.max(prev[1], curr[1]);
            }
        }
        int[][] solu = new int[result.size()][2];
        for(int i = 0; i < result.size(); i++) {
            solu[i] = result.get(i);
        }
        return solu;
    }
}


