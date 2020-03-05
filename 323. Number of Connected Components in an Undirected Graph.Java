
/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2

Example 2:
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1

Note: You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/


// method 1: BFS
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] visited = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                count++;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = 1;
                while(!queue.isEmpty()) {
                    int curr = queue.poll();
                    for(int neighbor: graph.get(curr)) {
                        if(visited[neighbor] == 0) {
                            visited[neighbor] = 1;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }// end i
        return count;
    }
}


