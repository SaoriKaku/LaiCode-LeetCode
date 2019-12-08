
/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*/


// method 1: DFS, Time Limit Exceeded 
// time complexity: O(2^n), space complexity: O(n)
class Solution {
    public int climbStairs(int n) {
        if(n < 0) {
            return 0;
        }
        if(n == 0) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}


// method 2: recursion with memoization
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int climbStairs(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        return climbStairsHelper(n, ways);
    }
     public int climbStairsHelper(int i, int[] ways) {
        if(ways[i] == 0) {
            ways[i] = climbStairsHelper(i - 1, ways) + climbStairsHelper(i - 2, ways);
        }
        return ways[i];
    }
}


// method 3: DP
// time complexity: O(n), space complexity: O(n)
class Solution {
    /*
    4: 5
    1 + 1 + 1 + 1
    2 + 1 + 1
    1 + 2 + 1
    1 + 1 + 2 
    2 + 2
    5: 8
    1 + 1 + 1 + 1 + 1 = 1
    1 + 1 + 1 + 2
    1 + 1 + 2 + 1
    1 + 2 + 1 + 1
    2 + 1 + 1 + 1 = 4
    2 + 2 + 1
    2 + 1 + 2
    1 + 2 + 2 = 3
    */
    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }
        // 0 - n
        int[] result = new int[n + 1];
        // base case:
        result[0] = 1;
        result[1] = 1;
        /* induction rule: 
           result[i] = 1 step + 2 steps;
           result[i] = result[i - 1] + result[i - 2];
        */ 
        for(int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }
}

