
/*
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 
There is at least one empty seat, and at least one person sitting.
Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 
Return that maximum distance to closest person.

Example 1:
Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.

Example 2:
Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Note:
1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
*/


// method 1: two pointers
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int maxDistToClosest(int[] seats) {
        if(seats == null || seats.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int maxDist = 0;
        while(right < seats.length) {
            if(seats[right] == 0) {
                while(right < seats.length && seats[right] == 0) {
                    right++;
                }
                int dist = 0;
                if(left == 0 || right == seats.length) {
                    dist = right - left;
                }
                else {
                    dist = (right - 1 - left) / 2 + 1;
                }
                maxDist = Math.max(maxDist, dist);
            }
            right++;
            left = right; 
        }
        return maxDist;
    }
}

