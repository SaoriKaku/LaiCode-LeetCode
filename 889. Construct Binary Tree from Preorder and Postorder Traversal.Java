
/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.

Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 
Note:
1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
*/


// method 1: recursion + map
// time complexity: O(n), space complexity: O(logn + n)
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre == null || pre.length == 0 || post == null || post.length == 0) {
            return null;
        }
        Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return constructFromPrePostHelper(pre, 0, pre.length - 1, postMap, 0, post.length - 1);
    }
    public TreeNode constructFromPrePostHelper(int[] pre, int preStart, int preEnd, Map<Integer, Integer>postMap, int postStart, int postEnd) {
        if(preStart > preEnd) {
            return null;
        }
        if(preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int leftSubRootInPre = preStart + 1;
        int leftSubRootInPost = postMap.get(pre[leftSubRootInPre]);
        int leftSubEndInPre = leftSubRootInPre + leftSubRootInPost - postStart;
        root.left = constructFromPrePostHelper(pre, leftSubRootInPre, leftSubEndInPre, postMap, postStart, leftSubRootInPost);
        root.right = constructFromPrePostHelper(pre, leftSubEndInPre + 1, preEnd, postMap, leftSubRootInPost + 1, postEnd - 1);
        return root;
    }
}





