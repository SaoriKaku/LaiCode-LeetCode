
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/


// method 1: two arrays to find highest bar to its left and right, unit(width) = 1
// result += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }
        int[] leftMaxHeight = new int[height.length];
        int leftMax = 0;
        for(int i = 0; i < height.length; i++) {
            leftMax = Math.max(leftMax, height[i]);
            leftMaxHeight[i] = leftMax;
        }
        int[] rightMaxHeight = new int[height.length];
        int rightMax = 0;
        for(int i = height.length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, height[i]);
            rightMaxHeight[i] = rightMax;
        }
        int result = 0;
        for(int i = 0; i < height.length; i++) {
            result += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }
        return result;
    }
}


// method 2: stack to find highest bar to its left and right, unit(width) = right - left, not 1
// result += (Math.min(leftMaxHeight, rightMaxHeight) - height[i]) * (right - left - 1);
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        for(int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int currIndex = stack.pop();
                int rightIndex = i;
                if(!stack.isEmpty()) {
                    int leftIndex = stack.peek();
                    result += (Math.min(height[rightIndex], height[leftIndex]) - height[currIndex]) * (rightIndex - leftIndex - 1);
                }
            }
            stack.push(i);
        }
        return result;
    }
}




