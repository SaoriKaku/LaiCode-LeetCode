
/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer.
*/


// method 1: bfs
// time complexity: O(n), space complexity: O(n)
/*
            1
        /      \
      1(2)       1(3)
     / \        / \
   1(4)  n(5)  n(6)  1(7)
  / \   / \   / \   / \
 1   n n   n n   n n   1 
 8   9 10 11 12 13 14  15
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, 1);
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int startIndex = 0;
            int lastIndex = 0;
            for(int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(i == 0) {
                    startIndex = map.get(currNode);
                }
                if(i == size - 1) {
                    lastIndex = map.get(currNode);
                }
                if(currNode.left != null) {
                    queue.offer(currNode.left);
                    map.put(currNode.left, 2 * map.get(currNode));
                }
                if(currNode.right != null) {
                    queue.offer(currNode.right);
                    map.put(currNode.right, 2 * map.get(currNode) + 1);
                }
            }
            result = Math.max(result, lastIndex - startIndex + 1);
        }
        return result;
    }
}


