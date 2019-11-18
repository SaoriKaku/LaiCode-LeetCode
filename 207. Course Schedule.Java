
/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/


/*
two values for dfs to return, has cycle return true, otherwise return false
for each node:
    if not marked:
        if(dfs(node) == CYCLE) return CYCLE
    return OK
dfs(node) {
    if node is marked as visited, return OK
    if node is marked as visiting, return CYCLE
    mark node as visiting
    for each newNode in node.neighbors:
        if dfs(newNode) == CYCLE, return CYCLE
    mark node as visited
    add node to the head of ordered_list
    return OK
}
// method 1: topological sort by dfs
// time complexity: O(n!), space complexity: O(n^2)
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        List<List<Integer>> graph = new ArrayList<>();
        // 0: untouched, 1: visiting, 2: visited
        int[] state = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            List<Integer> curr = new ArrayList<>();
            graph.add(curr);
        }
        for(int i = 0; i < prerequisites.length; i++) {
            int first = prerequisites[i][1];
            int second =  prerequisites[i][0];
            // first -> second, take course first at beginning, then second
            graph.get(first).add(second);
        }
        for(int i = 0; i < numCourses; i++) {
            if(dfs(i, state, graph)) return false; // there is a cycle
        }
        return true;
    }
    public boolean dfs(int index, int[] state, List<List<Integer>> graph) {
        if(state[index] == 1) return true;
        if(state[index] == 2) return false;
        state[index] = 1;
        for(int number: graph.get(index)) {
            if(dfs(number, state, graph)) return true;
        }
        state[index] = 2;
        return false;
    }
}



