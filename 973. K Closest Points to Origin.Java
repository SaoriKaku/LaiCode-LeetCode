
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 
Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
*/


// method 1: minHeap
// time complexity: O(nlogn)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new MyComparator());
        for(int i = 0; i < points.length; i++) {
            minHeap.offer(points[i]);
        }
        int[][] result = new int[K][2];
        for(int i = 0; i < K; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
    
    class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] point1, int[] point2) {
            return point1[0] * point1[0] + point1[1] * point1[1] - point2[0] * point2[0] - point2[1] * point2[1];
        }
    }
}


// method 2: maxHeap
// time complexity: O(nlogk)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new MyComparator());
        for(int i = 0; i < points.length; i++) {
            maxHeap.offer(points[i]);
            if(maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        int[][] result = new int[K][2];
        for(int i = 0; i < K; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
    
    class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] point1, int[] point2) {
            return point2[0] * point2[0] + point2[1] * point2[1] - point1[0] * point1[0] - point1[1] * point1[1];
        }
    }
}


// method 3: sort array
// time complexity: O(nlogn)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new MyComparator());
        int[][] result = new int[K][2];
        for(int i = 0; i < K; i++) {
            result[i] = points[i];
        }
        return result;
    }
    
    class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] point1, int[] point2) {
            return point1[0] * point1[0] + point1[1] * point1[1] - point2[0] * point2[0] - point2[1] * point2[1];
        }
    }
}


// method 4: quick select
// time compleixty: O(n), worse case: O(n^2), space complexity: O(1)
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            // 把当前比pivot小的right赋值给left
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            // 把当前比pivot大的left赋值给right
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}


