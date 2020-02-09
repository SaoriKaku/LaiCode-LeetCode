
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // recursion: preorder
        if(root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        //System.out.println(result.toString());
        return result.toString();
    }
    public void serializeHelper(TreeNode root, StringBuilder result) {
        if(root == null) {
            result.append("# ");
            return;
        }
        result.append(root.val + " ");
        serializeHelper(root.left, result);
        serializeHelper(root.right, result);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) {
            return null;
        }
        char[] input = data.toCharArray();
        int[] index = new int[1];
        return deserializeHelper(input, index);
    }
    public TreeNode deserializeHelper(char[] input, int[] index) {
        StringBuilder data = new StringBuilder();
        while(index[0] < input.length && input[index[0]] != ' ') {
            data.append(input[index[0]]);
            index[0]++;
        }
        index[0]++; // jump " "
        String number = data.toString();
        //System.out.println("number = " + number);
        if(number.equals("#")) {
            return null;
        }
        else {
            int value = Integer.valueOf(number);
            TreeNode root = new TreeNode(value);
            root.left = deserializeHelper(input, index);
            root.right = deserializeHelper(input, index);
            return root;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

