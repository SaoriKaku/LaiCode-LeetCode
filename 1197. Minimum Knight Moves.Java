
/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 
Constraints:
|x| + |y| <= 300
*/


// method 1: bfs
// time complexity: O(step), space complexity: O(8^step)
class Solution {
    public static final int[][] pattern = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited.add(0 + "," + 0);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] currCoor = queue.poll();
                int currX = currCoor[0];
                int currY = currCoor[1];
                if(currX == x && currY == y) {
                    return step;
                }
                for(int j = 0; j < pattern.length; j++) {
                    int nextX = currX + pattern[j][0];
                    int nextY = currY + pattern[j][1];
                    if(nextX >= -1 && nextY >= -1 && visited.add(nextX + "," + nextY) && Math.abs(nextX) + Math.abs(nextY) <= 300) {
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
            step++;
        }
        return step;
    }
}




