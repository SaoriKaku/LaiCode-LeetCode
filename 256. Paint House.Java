
/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note: All costs are positive integers.

Example:
Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.
*/


// method 1: DP(1D)
// time complexity: O(3n), space complexity: O(n)
class Solution {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }
        int row = costs.length;
        int[][] costSoFar = new int[row][3];
        /*
        row = num of house, col = color
            r   g   b
        #1  17  2   17
        #2  16  16  5
        #3  14  3   19
            r        g         b
        #1  17       2         17
        #2  16+2=18  16+17=33  5+2=7
        #3  14+7=21  3+7=10    36+18=54
        */
        costSoFar[0][0] = costs[0][0];
        costSoFar[0][1] = costs[0][1];
        costSoFar[0][2] = costs[0][2];
        for(int i = 1; i < row; i++) {
            costSoFar[i][0] = costs[i][0] + Math.min(costSoFar[i-1][1], costSoFar[i-1][2]);
            costSoFar[i][1] = costs[i][1] + Math.min(costSoFar[i-1][0], costSoFar[i-1][2]);
            costSoFar[i][2] = costs[i][2] + Math.min(costSoFar[i-1][0], costSoFar[i-1][1]);
        }
        int result = Math.min(costSoFar[row - 1][0], costSoFar[row - 1][1]);
        return Math.min(result, costSoFar[row - 1][2]);
    }
}

