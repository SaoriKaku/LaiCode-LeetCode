
/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
Input: [5,2,6,1]
Output: [2,1,1,0] 

Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
*/


// method 1: build tree
// time complexity: O(nlogn + n), space complexity: O(n)
class Solution {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int duplicate;
        int leftNodeCount;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        result.add(0);
        TreeNode root = new TreeNode(nums[n - 1]);
        root.duplicate++;
        for(int i = n - 2; i >= 0; i--) {
            result.add(insert(root, nums[i]));
        }
        Collections.reverse(result);
        return result;
    }

    public int insert(TreeNode root, int key) {
        int sum = 0;
        while(root.val != key) {
            if(root.val > key) { // go root's left
                if(root.left == null) {
                    root.left = new TreeNode(key);
                }
                root.leftNodeCount++;
                root = root.left;
            }
            else { // go root's right
                if(root.right == null) {
                    root.right = new TreeNode(key);
                }
                sum += root.leftNodeCount + root.duplicate;
                root = root.right;
            }
        }
        root.duplicate++;
        return sum + root.leftNodeCount;
    }
}


