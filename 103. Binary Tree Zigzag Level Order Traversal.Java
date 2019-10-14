
/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
/*
   1       pollFirst, offerLast
  / \
 2   3     pollLast, offerFirst
 /\  /\
4 5  7 8   pollFirst, offerLast
/\
9 10
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        int depth = 1;
        while(!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> currList = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                if(depth % 2 == 1) {
                    TreeNode currNode = deque.pollFirst();
                    currList.add(currNode.val);
                    if(currNode.left != null) {
                        deque.offerLast(currNode.left);
                    }
                    if(currNode.right != null) {
                        deque.offerLast(currNode.right);
                    }
                }
                else {
                    TreeNode currNode = deque.pollLast();
                     currList.add(currNode.val);
                    if(currNode.right != null) {
                        deque.offerFirst(currNode.right);
                    }
                    if(currNode.left != null) {
                        deque.offerFirst(currNode.left);
                    }
                }
            }
            result.add(currList);
            depth++;
        }
        return result;
    }
}

