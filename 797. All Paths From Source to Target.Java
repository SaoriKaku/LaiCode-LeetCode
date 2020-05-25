
/*
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Note:
The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/


// method 1: dfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if(graph == null || graph.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(graph, 0, path, result);
        return result;
    }
    public void dfs(int[][] graph, int index, List<Integer> path, List<List<Integer>> result) {
        path.add(index);
        if(index == graph.length - 1) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        for(int i = 0; i < graph[index].length; i++) {
            dfs(graph, graph[index][i], path, result);
        }
        path.remove(path.size() - 1);
    }
}



