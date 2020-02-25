
/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0

Follow up: Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/


// method 1: union find
// time complexity: O(m * n + k), m * n build union find, k union find, space complexity: O(m * n)
class Solution {
    class UnionFind {
        private int[] parents;
        private int[] ranks;
        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for(int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }
        public int find(int i) {
            while(parents[i] != i) {
                parents[i] = parents[parents[i]];
                i = parents[i];
            }
            return i;
        }
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI == rootJ) {
                return false;
            }
            if(ranks[rootI] > ranks[rootJ]) {
                parents[rootJ] = rootI;
            }
            else if(ranks[rootJ] > ranks[rootI]) {
                parents[rootI] = rootJ;
            }
            else {
                parents[rootJ] = rootI;
                ranks[rootI]++;
            }
            return true;
        }
    }
    public int[][] patterns = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if(positions == null || positions.length == 0) {
            return new ArrayList<>();
        }
        UnionFind uf = new UnionFind(m * n);
        int[][] islands = new int[m][n];
        int count = 0;
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            int prevIndex = x * n + y;
            if(islands[x][y] == 1) {
                result.add(count);
                continue;
            }
            islands[x][y] = 1;
            count++;
            for(int j = 0; j < 4; j++) {
                int nextX = x + patterns[j][0];
                int nextY = y + patterns[j][1];
                if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && islands[nextX][nextY] == 1) {
                    int currIndex = nextX * n + nextY;
                    if(uf.union(prevIndex, currIndex)) {
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}


