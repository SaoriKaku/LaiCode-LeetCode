
/*
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.
Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

Example 1:
Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

Example 2:
Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.

Example 3:
Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 
Note:
1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/


// method 1: math
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean isRobotBounded(String instructions) {
        if(instructions == null || instructions.length() == 0) {
            return true;
        }
        // dir: 1 facing north, 2 facing east, 3 facing south, 4 facing west
        int i = 0, j = 0, dir = 1;
        for(char curr: instructions.toCharArray()) {
            if(curr == 'G') {
                if(dir == 1) {
                    j++;
                }
                else if(dir == 2) {
                    i++;
                }
                else if(dir == 3) {
                    j--;
                }
                else {
                    i--;
                }
            }
            else if(curr == 'R') {
                dir = dir == 4 ? 1 : dir + 1;
            }
            else if(curr == 'L') {
                dir = dir == 1 ? 4 : dir - 1;
            }
        }
        return i == 0 && j == 0 || dir > 1;
    }
}

