
/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Note: n and k are non-negative integers.

Example:
Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
*/


// method 1: DP(1D)
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    每个格子可以有两种可能：i same with i-1 + i diff with i-1
    1. ith different from the i-1 th post? k-1
    diff[i] = total[i-1] * (k-1)，i为i-1每一个可能性乘以(k-1)，1为i-1的一种可能性
    2. ith post make it the same as the i-1th 
    same[i] = diff[i-1] * 1, i和i-1可能性相同

    total[i] = total[i-1]*(k-1) + diff[i-1]
             = total[i-1]*(k-1) + total[i-2]*(k-1)
    */
    public int numWays(int n, int k) {
        if(n == 0 || k == 0) {
            return 0;
        }
        if(n == 1) {
            return k;
        }
        if(n == 2) {
            return k * k;
        }
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k * k;
        for(int i = 2; i < n; i++) {
            dp[i] = dp[i-1] * (k-1) + dp[i-2] * (k-1);
        }
        return dp[n-1];
    }
}



