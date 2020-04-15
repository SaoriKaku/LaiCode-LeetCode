
/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:
Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
Follow up:
Could you solve it in O(nk) runtime?
*/


// method 1: DP(2D)
// time complexity: O(n * k), space complexity: O(1)
class Solution {
    /*
    costSum[i] = cost[i] + min(all costSum[i-1] except the same col)
    do not need to traverse the previous row, 
    recorde the 1st 2nd max in previous row when handle previous row
    */
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }
        int row = costs.length, col = costs[0].length;
        int currMin1 = 0, currMin2 = 0;
        for(int i = 0; i < row; i++) {
            int prevMin1 = currMin1;
            int prevMin2 = currMin2;
            currMin1 = -1;
            currMin2 = -1;
            for(int j = 0; j < col; j++) {
                if(i == 0 || costs[i-1][j] == prevMin1) {
                    costs[i][j] += prevMin2;
                }
                else {
                    costs[i][j] += prevMin1;
                }
                // 1. update currMin1, currMin2 = currMin1
                // 2. update currMin2
                if(currMin1 == -1 || costs[i][j] < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = costs[i][j];
                }
                else if(currMin2 == -1 || costs[i][j] < currMin2) {
                    currMin2 = costs[i][j];
                }
            }
        }
        return currMin1;
    }
}

