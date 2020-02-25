
/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

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
    */
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) {
            return new int[]{};
        }
        if(prerequisites == null || prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for(int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
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
        List<Integer> solu = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            if(dfs(i, state, solu, graph)) return new int[]{}; // there is a cycle
        }
        // in solu, the first one is the last course we should take
        // because it does not has incoming edge, so first added to solu
        int[] result = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            result[i] = solu.get(numCourses - 1 - i);
        }
        return result;
    }
    public boolean dfs(int index, int[] state, List<Integer> solu, List<List<Integer>> graph) {
        if(state[index] == 1) return true;
        if(state[index] == 2) return false;
        state[index] = 1;
        for(int number: graph.get(index)) {
            if(dfs(number, state, solu, graph)) return true;
        }
        state[index] = 2;
        solu.add(index);
        return false;
    }
}


// method 2: bfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // map<node, list of successors>
        ArrayList<Integer> successors[] = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            successors[i] = new ArrayList<>();
        }
        // map<node, indegree of node>
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            int prev = prerequisites[i][1];
            int curr = prerequisites[i][0];
            successors[prev].add(curr);
            indegree[curr]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] result = new int[numCourses];
        int index = 0;
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            result[index++] = curr;
            for(int successor: successors[curr]) {
                indegree[successor]--;
                if(indegree[successor] == 0) {
                    queue.add(successor);
                }
            }
        }
        return index == numCourses ? result : new int[0];
    }
}


