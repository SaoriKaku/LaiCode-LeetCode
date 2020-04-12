
/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:
The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
*/


// method 1: time and space exceed limit
class Solution {
    /*
    start point: buildings[0][0];
    last point: building[n-1][1];
    len = last - start
    int[] height: max building height at i(i + start)
    [2, 9, 10]
    for(int = 2; i <= 9; i++) {
        height[i-start] = max(height[i-start], 10);
    }
    */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0) {
            return result;
        }
        int start = buildings[0][0];
        int last = buildings[buildings.length - 1][1];
        int len = last - start + 1;
        int[] heights = new int[len];
        for(int i = 0; i < buildings.length; i++) {
            int left = buildings[i][0];
            int right = buildings[i][1];
            int currHeight = buildings[i][2];
            for(int j = left; j < right; j++) {
                heights[j - start] = Math.max(heights[j - start], currHeight);
            }
        }
        // for(int i = 0; i < heights.length; i++) {
        //     int curr = i + 2;
        //     System.out.println(curr + ": " + heights[i]);
        // }
        for(int i = 0; i < heights.length; i++) {
            if(i == 0 || heights[i] != heights[i-1]) {
                List<Integer> newHeight = new ArrayList<>();
                newHeight.add(i + start);
                newHeight.add(heights[i]);
                result.add(newHeight);
            }
        }
        return result;
    }
}


// method 2: heap, line sweep
// time complexity: O(n + nlogn + n*k), space complexity: O(n + k)
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if(buildings == null || buildings.length == 0) {
            return result;
        }
        List<int[]> events = new ArrayList<>();
        // in-event: start point, -1 * height; out-event: end point, height
        for(int[] building: buildings) {
            events.add(new int[]{building[0], -1 * building[2]});
            events.add(new int[]{building[1], building[2]});
        }
        Collections.sort(events, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0]; // smaller x comes first
            }
            else {
                return a[1] - b[1]; // smaller height comes first
            }
        });
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        maxHeap.offer(0);
        int prevHeight = 0;
        for(int[] event: events) {
            if(event[1] < 0) { // in-event
                maxHeap.offer(-1 * event[1]);
            }
            else {
                maxHeap.remove(event[1]);
            }
            int currHeight = maxHeap.peek();
            if(currHeight != prevHeight) {
                List<Integer> curr = new ArrayList<>();
                curr.add(event[0]);
                curr.add(currHeight);
                result.add(curr);
                prevHeight = currHeight;
            }
        }
        return result;
    }
}



