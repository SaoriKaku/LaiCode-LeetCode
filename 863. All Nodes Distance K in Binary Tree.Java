
/*
We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
Output: [7,4,1]
Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 
Note:
The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// method 1: dfs + bfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if(root == null || target == null || K < 0) {
            return new ArrayList<>();
        }
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(K == 0) {
                    result.add(currNode.val);
                    continue;
                }
                for(TreeNode neighbor: graph.get(currNode)) {
                    if(visited.add(neighbor)) {
                        queue.offer(neighbor);
                    }
                }
            }
            if(K == 0) return result;
            K--;
        }
        return result;
    }
    public void buildGraph(TreeNode curr, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
        if(curr == null) return;
        if(!graph.containsKey(curr)) {
            graph.put(curr, new ArrayList<>());
            if(parent != null) {
                graph.get(curr).add(parent);
                graph.get(parent).add(curr);
            }
        }
        buildGraph(curr.left, curr, graph);
        buildGraph(curr.right, curr, graph);
    }
}

