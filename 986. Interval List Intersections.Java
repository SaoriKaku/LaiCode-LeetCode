
/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.
(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 
Note:
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/


// method 1: interval intersection
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0) {
            return new int[0][0];
        }
        int i = 0, j = 0;
        List<int[]> result = new ArrayList<>();
        while(i < A.length && j < B.length) {
            if(A[i][1] < B[j][0]) { // [1, 2], [3, 4]
                i++;
                //System.out.println("i++");
                continue;
            }
            if(A[i][0] > B[j][1]) { // [3, 4], [1, 2]
                j++;
                //System.out.println("j++");
                continue;
            }
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            int[] newInterval = new int[]{start, end};
            //System.out.println("new: " + Arrays.toString(newInterval));
            result.add(newInterval);
            if(A[i][1] < B[j][1]) {
                i++;
            }
            else {
                j++;
            }
        }
        int[][] solu = new int[result.size()][2];
        for(i = 0; i < result.size(); i++) {
            solu[i] = result.get(i);
        }
        return solu;
    }
}

