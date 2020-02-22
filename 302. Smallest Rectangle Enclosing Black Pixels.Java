
/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:
Input:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2

Output: 6
*/


// method 1: dfs
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int minArea(char[][] image, int x, int y) {
        if(image == null || image.length == 0) {
            return 0;
        }
        int[] area = new int[]{x, x, y, y}; // top, bottom, left, right
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        dfs(image, area, dir, x, y, image.length, image[0].length);
        //System.out.println(Arrays.toString(area));
        return (area[1] - area[0] + 1) * (area[3] - area[2] + 1);
    }
    public void dfs(char[][] image, int[] area, int[][] dir, int x, int y, int row, int col) {
        if(x < 0 || x >= row || y < 0 || y >= col || image[x][y] == '0') {
            return;
        }
        image[x][y] = '0';
        area[0] = Math.min(area[0], x);
        area[1] = Math.max(area[1], x);
        area[2] = Math.min(area[2], y);
        area[3] = Math.max(area[3], y);
        for(int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            //System.out.println(nextX + " " + nextY);
            dfs(image, area, dir, nextX, nextY, row, col);
        }
    }
}


