
/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:
Input: [2,1,5,6,2,3]
Output: 10
*/


// method 1: brute force
// time complexity: O(n^2), space complexity: O(1)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length, maxArea = 0;
        for(int i = 0; i < n; i++) {
            int minHeight = heights[i];
            int area = minHeight;
            maxArea = Math.max(maxArea, area);
            for(int j = i + 1; j < n; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                area = minHeight * (j - i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}


// method 2: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }
        // when a index pop from stack, calulate the area with the maxHeight = height[index]
        // store index in stack, but the index with increasing height
        int maxArea = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
            }
            else {
                int right = stack.peek();
                while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    int currHeight = heights[stack.pop()];
                    int left = -1;
                    if(!stack.isEmpty()) {
                        left = stack.peek();
                    }
                    maxArea = Math.max(maxArea, currHeight * (right - left));
                }
                stack.push(i);
            }
        }
        if(!stack.isEmpty()) {
            int right = stack.peek();
            while(!stack.isEmpty()) {
                int currHeight = heights[stack.pop()];
                int left = -1;
                if(!stack.isEmpty()) {
                    left = stack.peek();
                }
                maxArea = Math.max(maxArea, currHeight * (right - left));
            }
        }
        return maxArea;
    }
}


// method 2: stack another version
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }
        // when a index pop from stack, calulate the area with the maxHeight = height[index]
        // store index in stack, but the index with increasing height
        int maxArea = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            //int right = stack.peek() + 1 = i;
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int currHeight = heights[stack.pop()];
                int left = -1;
                if(!stack.isEmpty()) {
                    left = stack.peek();
                }
                maxArea = Math.max(maxArea, currHeight * (i - left - 1));
            }
            stack.push(i);
        }
        if(!stack.isEmpty()) {
            int right = stack.peek();
            while(!stack.isEmpty()) {
                int currHeight = heights[stack.pop()];
                int left = -1;
                if(!stack.isEmpty()) {
                    left = stack.peek();
                }
                maxArea = Math.max(maxArea, currHeight * (right - left));
            }
        }
        return maxArea;
    }
}



