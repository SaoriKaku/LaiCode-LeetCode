
/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:
Input:
org: [1,2,3], seqs: [[1,2],[1,3]]
Output:
false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

Example 2:
Input:
org: [1,2,3], seqs: [[1,2]]
Output:
false
Explanation:
The reconstructed sequence can only be [1,2].

Example 3:
Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
Output:
true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:
Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes.
*/


// method 1: topological sort
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if(seqs == null || seqs.size() == 0) {
            return false;
        }
        // build map<node, list of successors>
        Map<Integer, Set<Integer>> successors = new HashMap<>();
        // build map<node, indegree of node>
        Map<Integer, Integer> indegree = new HashMap<>();
        // initialize
        for(List<Integer> currList: seqs) {
            for(int currNum: currList) {
                if(!successors.containsKey(currNum)) {
                    successors.put(currNum, new HashSet<>());
                    indegree.put(currNum, 0);
                }
            }
        }
        // build successors
        for(List<Integer> currList: seqs) {
            for(int i = 1; i < currList.size(); i++) {
                int curr = currList.get(i);
                int prev = currList.get(i - 1);
                successors.get(prev).add(curr);
            }
        }
        // build indegree
        for(int curr: successors.keySet()) {
            for(int successor: successors.get(curr)) {
                indegree.put(successor, indegree.get(successor) + 1);
            }
        }
        if(indegree.size() != org.length) {
            return false;
        }
        //System.out.println("successors: " + successors);
        //System.out.println("indegree: " + indegree);
        Queue<Integer> queue = new LinkedList<>();
        for(int curr: successors.keySet()) {
            if(indegree.get(curr) == 0) {
                queue.add(curr);
            }
        }
        //System.out.println("queue: " + queue);
        int index = 0;
        while(!queue.isEmpty()) {
            if(queue.size() > 1) {
                return false;
            }
            int curr = queue.poll();
            if(org[index++] != curr) {
                //System.out.println("org: " + org[index - 1] + ", curr = " + curr);
                return false;
            }
            for(int successor: successors.get(curr)) {
                indegree.put(successor, indegree.get(successor) - 1);
                if(indegree.get(successor) == 0) {
                    queue.offer(successor);
                }
            }
        }
        return index == org.length;
    }
}
