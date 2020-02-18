
/*
Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:
  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:
  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSumHelper(root, map);
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for(int num: map.keySet()) {
            int count = map.get(num);
            if(count == max) {
                list.add(num);
            }
            else if(count > max) {
                max = count;
                list.clear();
                list.add(num);
            }
        }
        int[] result = new int[list.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    public int findFrequentTreeSumHelper(TreeNode root, Map<Integer, Integer> map) {
        if(root == null) {
            return 0;
        }
        int left = findFrequentTreeSumHelper(root.left, map);
        int right = findFrequentTreeSumHelper(root.right, map);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
