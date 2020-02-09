
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

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
        if(root == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        //System.out.println(result.toString());
        return result.toString();
    }
    private void serializeHelper(TreeNode root, StringBuilder result) {
        if(root == null) {
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
        return deserializeHelper(input, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode deserializeHelper(char[] input, int[] index, int min, int max) {
        if(index[0] == input.length) {
            return null;
        }
        int mark = index[0];
        StringBuilder data = new StringBuilder();
        while(index[0] < input.length && input[index[0]] != ' ') {
            data.append(input[index[0]]);
            index[0]++;
        }
        index[0]++; //jump space
        int value = Integer.valueOf(data.toString());
        //System.out.println("value = " + value);
        if(value > max || value < min) {
            index[0] = mark;
            return null;
        }
        TreeNode root = new TreeNode(value);
        root.left = deserializeHelper(input, index, min, value);
        root.right = deserializeHelper(input, index, value, max);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


