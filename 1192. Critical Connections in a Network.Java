
/*
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 
Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
*/


/*
    ids = [0, 0, ...];
    low = [0, 0, ...];
    visited = [false, false, ...];
    function findBridge() {
        bridges = [];
        for(int i = 0; i < n; i++) {
            if(!visited(i)) {
                dfs(i, -1, bridges);
            }
        }
        return bridges;
    }
    function dfs(at, parent, bridges) {
        visited[at] = true;
        id = id + 1;
        low[at] = ids[at] = id;
        for(to: g[at]) {
            if(to == parent) {
                continue;
            }
            if(!visited[to]) {
                dfs(to, at, bridges);
                low[at] = min(low[at], low[to]);
                if(ids[at] < low[to]) {
                    bridges.add(at);
                    bridges.add(to);
                }
            }
            else {
                low[at] = min(low[at], ids[to]);
            }
        }
    }
*/
// method 1: runtime: 30%, space: 100%
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        if(connections.size() == 0) {
            return result;
        }
        List<List<Integer>> graph = new ArrayList<>();
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        int[] low = new int[n];
        int[] count = new int[1];
        count[0] = 0;
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0);
            int to = connections.get(i).get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        for(int i = 0; i < n; i++) {
            if(visited[i] == -1) {
                dfs(i, i, count, low, visited, graph, result);
            }
        }
        return result; 
    }
    
    public void dfs(int curr, int parent, int[] count, int[] low, int[] visited, List<List<Integer>> graph, List<List<Integer>> result) {
        visited[curr] = count[0];
        low[curr] = count[0];
        count[0]++;
        for(int next: graph.get(curr)) {
            if(next == parent) {
                continue;
            }
            if(visited[next] == -1) {
                dfs(next, curr, count, low, visited, graph, result);
                low[curr] = Math.min(low[curr], low[next]);
                if(visited[curr] < low[next]) {
                    List<Integer> solution = new ArrayList<>();
                    solution.add(curr);
                    solution.add(next);
                    result.add(solution);
                }
            }
            else {
                low[curr] = Math.min(low[curr], visited[next]);
            }
        }
    }
}


// method 2: runtime: 70%, space: 100%
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        if(connections.size() == 0) {
            return result;
        }
        List<Integer>[] graph = new ArrayList[n];
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        int[] low = new int[n];
        int[] count = new int[1];
        count[0] = 0;
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0);
            int to = connections.get(i).get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i = 0; i < n; i++) {
            if(visited[i] == -1) {
                dfs(i, i, count, low, visited, graph, result);
            }
        }
        return result; 
    }
    
    public void dfs(int curr, int parent, int[] count, int[] low, int[] visited, List<Integer>[] graph, List<List<Integer>> result) {
        visited[curr] = count[0];
        low[curr] = count[0];
        count[0]++;
        for(int next: graph[curr]) {
            if(next == parent) {
                continue;
            }
            if(visited[next] == -1) {
                dfs(next, curr, count, low, visited, graph, result);
                low[curr] = Math.min(low[curr], low[next]);
                if(visited[curr] < low[next]) {
                    List<Integer> solution = new ArrayList<>();
                    solution.add(curr);
                    solution.add(next);
                    result.add(solution);
                }
            }
            else {
                low[curr] = Math.min(low[curr], visited[next]);
            }
        }
    }
}

