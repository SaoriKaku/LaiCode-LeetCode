
/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/


// method 1: DP(1D)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        /*
           2
          3 4
         6 5 7
        4 1 8 3
        layer = 2, index = 0, 6 + min(4, 1)
        */
        int n = triangle.size();
        int[] sum = new int[n];
        for(int i = 0; i < triangle.get(n-1).size(); i++) {
            sum[i] = triangle.get(n-1).get(i);
        }
        for(int i = n - 2; i >= 0; i--) { // i = layer
            for(int j = 0; j <= i; j++) { // j = index in each layer
                sum[j] = Math.min(sum[j], sum[j + 1]) + triangle.get(i).get(j);
            }
        }
        return sum[0];
    }
}
