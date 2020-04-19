
/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.

Examples 1:
Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:
Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]

Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
*/


// method 1: bfs + HashMap
// time complexity: O(n), space complexity: O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // map<key: weight, value: list of nodes on the same vertical line>
        Map<Integer, List<Integer>> lineListMap = new HashMap<>();
        // map<key: node, value: line number>
        Map<TreeNode, Integer> nodeLineMap = new HashMap<>();
        queue.offer(root);
        nodeLineMap.put(root, 0);
        int min = 0, max = 0;
        while(!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            int line = nodeLineMap.get(currNode);
            lineListMap.putIfAbsent(line, new ArrayList<>());
            lineListMap.get(line).add(currNode.val);
            if(currNode.left != null) {
                nodeLineMap.put(currNode.left, line - 1);
                queue.offer(currNode.left);
                min = Math.min(min, line - 1);
            }
            if(currNode.right != null) {
                nodeLineMap.put(currNode.right, line + 1);
                queue.offer(currNode.right);
                max = Math.max(max, line + 1);
            }
        }
        for(int i = min; i <= max; i++) {
            result.add(new ArrayList<>(lineListMap.get(i)));
        }
        return result;
    }
}





