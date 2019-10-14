
/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
*/

// 倒数第四个test过不去
class Solution {
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0) {
            return true;
        }
        HashSet<Integer> subset = new HashSet<>();
        HashSet<Integer> subset2 = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> generated = new HashSet<>();
        HashSet<Integer> expanded = new HashSet<>();
        for(int i = 0; i < graph.length; i++) {
            queue.offer(i);
        }
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(subset.isEmpty() && subset2.isEmpty()) {
                subset.add(curr);
                generated.add(curr);
            }
            if(expanded.contains(curr)) {
                continue;
            }
            if(!generated.contains(curr)) {
                //queue.add(curr);
                continue;
            }
            for(int i = 0; i < graph[curr].length; i++) {
                int neighbor = graph[curr][i];
                queue.add(neighbor);
                // curr in subset, neighbor in another subset
                if(subset.contains(curr)) {
                    if(generated.contains(neighbor)) {
                        if(subset.contains(neighbor)) {
                            System.out.println("curr = " + curr + " neighbor = " + neighbor);
                            System.out.println("subset: " + subset + " subset2: " + subset2);
                            return false;
                        }
                    }
                    else {
                        subset2.add(neighbor);
                    }
                }
                // curr is in another subset, neighbor in subset
                else {
                    if(generated.contains(neighbor)) {
                        if(subset2.contains(neighbor)) {
                            System.out.println("curr = " + curr + " neighbor = " + neighbor);
                            System.out.println("subset: " + subset + " subset2: " + subset2);
                            return false;
                        }
                    }
                    else {
                        subset.add(neighbor);
                    }  
                }
                generated.add(neighbor);
            }
            expanded.add(curr);
        }
        return true;
    }
}


class Solution {
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0) {
            return true;
        }
        // -1 not visited
        // 0 in one subset
        // 1 in other subset
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);
        //Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < graph.length; i++) {
            if(color[i] == -1) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                color[i] = 0;
                while(!queue.isEmpty()) {
                    int curr = queue.poll();
                    int currColor = color[curr];
                    int neighborColor = (currColor == 1) ? 0 : 1;
                    for(int j = 0; j < graph[curr].length; j++) {
                        int neighbor = graph[curr][j];
                        if(color[neighbor] == -1) {
                            queue.offer(neighbor);
                            color[neighbor] = neighborColor;
                        }
                        else if(color[neighbor] != neighborColor) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}



