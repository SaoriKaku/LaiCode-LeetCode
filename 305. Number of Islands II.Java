
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
    /*
    1. 逐个添加island到地图上，count+1
    2. 查找当前island的周围四个，
       如果可以union，说明之前两个island不相连，现在连接，count-1；
       如果无法union，说明之前就是连接的，也就是这个点之前也是别的点的邻居，所以不用考虑
    */
    class UnionFind{
        private int[] parent;
        private int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(parent, -1);
        }
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI == rootJ) return false;
            if(rank[rootI] == rank[rootJ]) {
                parent[rootI] = rootJ;
                rank[rootJ]++;
            }
            else if(rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            }
            else {
                parent[rootJ] = rootI;
            }
            return true;
        }
        public int find(int i) {
            while(parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
    }
    private int[][] pattern = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(positions == null || positions.length == 0) {
            return result;
        }
        UnionFind uf = new UnionFind(m * n);
        int count = 0;
        for(int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            int index = x * n + y;
            if(uf.parent[index] != -1) {
                result.add(count);
                continue;
            }
            uf.parent[index] = index;
            count++;
            for(int j = 0; j < pattern.length; j++) {
                int nextX = x + pattern[j][0];
                int nextY = y + pattern[j][1];
                int nextIndex = nextX * n + nextY;
                if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && uf.parent[nextIndex] != -1) {
                    if(uf.union(index, nextIndex)) {
                        count--;
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}


