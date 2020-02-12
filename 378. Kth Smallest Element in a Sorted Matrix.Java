
/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
return 13.

Note: You may assume k is always valid, 1 ≤ k ≤ n2.
*/


// method 1: heap
// time complexity: O(klogn), space complexity: O(n)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // int[] coordinate = {row, col};
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        // 1...k -> 0...k-1
        minHeap.offer(new int[]{0, 0});
        for(int i = 0; i < k - 1; i++) {
            int[] curr = minHeap.poll();
            //System.out.println(matrix[curr[0]][curr[1]]);
            int row = curr[0];
            int col = curr[1];
            if(row + 1 < matrix.length && col < matrix[0].length && !visited[row + 1][col]) {
                minHeap.offer(new int[]{row + 1, col});
                visited[row + 1][col] = true;
            }
            if(row < matrix.length && col + 1 < matrix[0].length && !visited[row][col + 1]) {
                minHeap.offer(new int[]{row, col + 1});
                visited[row][col + 1] = true;
            }
        }
        int[] result = minHeap.peek();
        return matrix[result[0]][result[1]];
    }
}




