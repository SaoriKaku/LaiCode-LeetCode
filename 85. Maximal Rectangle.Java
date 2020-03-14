
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/


// method 1: build arrar: DP(1D) + find max rectangle: stack
// time complexity: O(row * col), space complexity: O(2 * col)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col];
        int maxArea = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i == 0) {
                    height[j] = (int)(matrix[i][j] - '0');
                }
                else {
                    int number = (int)(matrix[i][j] - '0');
                    height[j] = number == 0 ? 0 : height[j] + 1;
                }
            }
            int area = maxRectangle(height);
            maxArea = Math.max(maxArea, area);
            //System.out.println(Arrays.toString(height));
            //System.out.println("area = " + area + ", maxArea = " + maxArea);
        }
        return maxArea;
    }
    public int maxRectangle(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0, n = height.length;
        for(int i = 0; i < n; i++) {
            // right = i = curr + 1
            // left = stack.peek() = curr - 1
            while(!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int currHeight = height[stack.pop()];
                int left = -1;
                if(!stack.isEmpty()) {
                    left = stack.peek();
                }
                maxArea = Math.max(maxArea, currHeight * (i - left - 1));
            }
            stack.push(i);
        }
        if(!stack.isEmpty()) {
            // right == curr;
            int right = stack.peek();
            while(!stack.isEmpty()) {
                int currHeight = height[stack.pop()];
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




