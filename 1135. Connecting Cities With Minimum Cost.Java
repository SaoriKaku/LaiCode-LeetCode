
/*
There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.

Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,

The cost is the sum of the connections' costs used.

Example 1:
Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:
Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.
 
Constraints:
1 <= n <= 104
1 <= connections.length <= 104
connections[i].length == 3
1 <= xi, yi <= n
xi != yi
0 <= costi <= 105
*/


// method1: minimum spanning tree
// time complexity: O(nlong), space complexity: O(n)
class Solution {
    /*
    1 -> 2: 1
    1 -> 3: 2
    3 -> 4: 4
    1 -> 4: 3
    */
    public int minimumCost(int n, int[][] connections) {
        // List<List<int[]>>: index: curr node, int[]: next node, value
        boolean[] visited = new boolean[n + 1];
        List<List<int[]>> graph = buildGraph(n, connections);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minHeap.offer(new int[]{1, 0});
        int visitedCount = 0;
        int cost = 0;
        while(!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            // System.out.println(Arrays.toString(curr));
            if(visited[curr[0]]) continue;
            visited[curr[0]] = true;
            visitedCount++;
            cost += curr[1];
            for(int[] next: graph.get(curr[0])) {
                // System.out.println(Arrays.toString(curr));
                if(!visited[next[0]]) {
                    minHeap.offer(next);
                }
            }
        }
        return visitedCount == n ? cost : -1;
    }
    private List<List<int[]>> buildGraph(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < connections.length; i++) {
            graph.get(connections[i][0]).add(new int[]{connections[i][1], connections[i][2]});
            graph.get(connections[i][1]).add(new int[]{connections[i][0], connections[i][2]});
        }
        return graph;
    }
}
