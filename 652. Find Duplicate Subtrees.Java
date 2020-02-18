
/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with same node values.

Example 1:
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
*/


// method 1: postorder
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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        findDuplicateSubtreesHelper(root, map, result);
        return result;
    }
    public String findDuplicateSubtreesHelper(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
        if(root == null) {
            return "#";
        }
        String left = findDuplicateSubtreesHelper(root.left, map, result);
        String right = findDuplicateSubtreesHelper(root.right, map, result);
        String curr = root.val + "," + left + "," + right;
        map.put(curr, map.getOrDefault(curr, 0) + 1);
        if(map.get(curr) == 2) {
            result.add(root);
        }
        return curr;
    }
}




