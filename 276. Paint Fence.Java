
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
    public int numWays(int n, int k) {
        /*
        total = keep[i] + diff[i]
        if color i = i - 1, keep[i] = keep[i - 1];
        if color i != i - 1, diff[i] = total[i - 1] * (k - 1);
        */
        if(n == 0 || k == 0) {
            return 0;
        }
        if(n == 1) {
            return k;
        }
        if(n == 2) {
            return k * k;
        }
        int[] keep = new int[n]; // i = i - 1
        int[] diff = new int[n]; // i != i - 1
        keep[0] = 0;
        diff[0] = k;
        for(int i = 1; i < n; i++) {
            keep[i] = diff[i - 1];
            diff[i] = (keep[i - 1] + diff[i - 1]) * (k - 1);
        }
        //System.out.println("keep: " + Arrays.toString(keep));
        //System.out.println("diff: " + Arrays.toString(diff));
        return keep[n - 1] + diff[n - 1];
    }
}




