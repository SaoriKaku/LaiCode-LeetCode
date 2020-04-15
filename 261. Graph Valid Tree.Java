
/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:
Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true

Example 2:
Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/

// method 1: BFS + HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        for(int i = 0; i < n; i++) {
            neighbors.put(i, new HashSet<>());
        }
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            neighbors.get(node1).add(node2);
            neighbors.get(node2).add(node1);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[n];
        queue.offer(0);
        visited[0] = 1;
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int neighbor: neighbors.get(curr)) {
                if(visited[neighbor] == 1) {
                    return false;
                }
                else {
                    queue.offer(neighbor);
                    visited[neighbor] = 1;
                }
                neighbors.get(neighbor).remove(curr);
                //neighbors.get(curr).remove(neighbor);
            }
        }
        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                return false;
            }
        }
        return true;
    }
}


// method 2: union find
// time complexity: O(n), space complexity: O(n)
class Solution {
    class UnionFind {
        int[] parent;
        int[] rank;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if(rootI == rootJ) {
                return false;
            }
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
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        // before each node's parent is itself
        // if node1 and node2 has the same root -> union again -> there is a cycle
        for(int[] edge: edges) {
            if(!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        int root = uf.find(0);
        for(int i = 1; i < n; i++) {
            if(uf.find(i) != root) {
                return false;
            }
        }
        return true;
    }
}
