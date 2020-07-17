
/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Example:
Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]
Return 4.

The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
After the rain, water is trapped between the blocks. The total volume of water trapped is 4.

Constraints:
1 <= m, n <= 110
0 <= heightMap[i][j] <= 20000
*/


// method 1: dfs
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    private int[][] pattern = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]]));
        int row = heightMap.length;
        int col = heightMap[0].length;
        for(int i = 0; i < row; i++) {
            visited.add(i + "," + 0);
            visited.add(i + "," + (col - 1));
            minHeap.offer(new int[]{i, 0});
            minHeap.offer(new int[]{i, col - 1});
        }
        for(int j = 0; j < col; j++) {
            visited.add(0 + "," + j);
            visited.add((row - 1) + "," + j);
            minHeap.offer(new int[]{0, j});
            minHeap.offer(new int[]{row - 1, j});
        }
        int result = 0;
        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int x = curr[0];
            int y = curr[1];
            int height = heightMap[x][y];
            for(int i = 0 ; i < pattern.length; i++) {
                int nextX = x + pattern[i][0];
                int nextY = y + pattern[i][1];
                if(nextX > 0 && nextX < row - 1 && nextY > 0 && nextY < col - 1 && visited.add(nextX + "," + nextY)) {
                    // 确定[nextX][nextY]能存多少水，当前[x][y]一定是最矮的边
                    result += Math.max(0, heightMap[x][y] - heightMap[nextX][nextY]);
                    // 把确定了水的新的小格子的高度更新到max([x][y], [nextX][nextY])
                    heightMap[nextX][nextY] = Math.max(heightMap[x][y], heightMap[nextX][nextY]);
                    minHeap.offer(new int[]{nextX, nextY});
                }
            }
        }
        return result;
    }
}


