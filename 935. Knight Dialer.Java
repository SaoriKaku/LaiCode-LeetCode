
/*
A chess knight can move as indicated in the chess diagram below:
This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.
Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.
How many distinct numbers can you dial in this manner?
Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:
Input: 1
Output: 10

Example 2:
Input: 2
Output: 20

Example 3:
Input: 3
Output: 46
 
Note:
1 <= N <= 5000
*/


// method 1: dfs
// time complexity: O(3^N), space complexity: O(N)
class Solution {
    public int knightDialer(int N) {
        if(N <= 0) return 0;
        int[][] hops = new int[][] {
            {4, 6}, // 0
            {6, 8}, // 1
            {7, 9}, // 2
            {4, 8}, // 3
            {0, 3, 9}, // 4
            {}, // 5
            {0, 1, 7}, // 6
            {2, 6}, // 7
            {1, 3}, // 8
            {2, 4} // 9
        };
        // memo[N][start] %= (int)1e9 + 7;
        int result = 0;
        for(int i = 0; i < 10; i++) {
            result += dfs(N - 1, i, hops);
        }
        return result;
    }
    public int dfs(int count, int row, int[][] hops) {
        if(count == 0) {
            return 1;
        }
        int result = 0;
        for(int i = 0; i < hops[row].length; i++) {
            result += dfs(count - 1, hops[row][i], hops);
        }
        return result;
    }
}


// method 2: dfs + memorization
// time complexity: O(10 * N), space complexity: O(10 * N)
class Solution {
    public int knightDialer(int N) {
        if(N <= 0) return 0;
        int[][] hops = new int[][] {
            {4, 6}, // 0
            {6, 8}, // 1
            {7, 9}, // 2
            {4, 8}, // 3
            {0, 3, 9}, // 4
            {}, // 5
            {0, 1, 7}, // 6
            {2, 6}, // 7
            {1, 3}, // 8
            {2, 4} // 9
        };
        // memo[N][start] %= (int)1e9 + 7;
        int[][] memo = new int[10][N];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < N; j++) {
                memo[i][j] = -1;
            }
        }
        int result = 0;
        for(int i = 0; i < 10; i++) {
            result += dfs(N - 1, i, hops, memo);
            result %= (int)(1e9 + 7);
        }
        return result;
    }
    public int dfs(int count, int row, int[][] hops, int[][] memo) {
        if(count == 0) {
            return 1;
        }
        if(memo[row][count] > -1) {
            return memo[row][count];
        }
        memo[row][count] = 0;
        for(int i = 0; i < hops[row].length; i++) {
            memo[row][count] += dfs(count - 1, hops[row][i], hops, memo);
            memo[row][count] %= (int)(1e9 + 7);
        }
        return memo[row][count];
    }
}

