
/*
You have d dice, and each die has f faces numbered 1, 2, ..., f.
Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.

Example 1:
Input: d = 1, f = 6, target = 3
Output: 1
Explanation: 
You throw one die with 6 faces.  There is only one way to get a sum of 3.

Example 2:
Input: d = 2, f = 6, target = 7
Output: 6
Explanation: 
You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

Example 3:
Input: d = 2, f = 5, target = 10
Output: 1
Explanation: 
You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.

Example 4:
Input: d = 1, f = 2, target = 3
Output: 0
Explanation: 
You throw one die with 2 faces.  There is no way to get a sum of 3.

Example 5:
Input: d = 30, f = 30, target = 500
Output: 222616187
Explanation: 
The answer must be returned modulo 10^9 + 7.
 
Constraints:
1 <= d, f <= 30
1 <= target <= 1000
*/


// method 1: dfs + memorization
// time complexity: O(f^d), space complexity: O(target * d)
class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        if(d <= 0 || f <= 0 || target <= 0) {
            return 0;
        }
        // key: sum + how many times left, value: how many ways to get sum
        Map<String, Integer> cache = new HashMap<>();
        return dfs(d, f, target, cache);
    }
    public int dfs(int count, int value, int sum, Map<String, Integer> cache) {
        if(count < 0 || sum < 0) {
            return 0;
        }
        if(count == 0 && sum == 0) {
            return 1;
        }
        String key = count + " " + sum;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        int result = 0;
        for(int i = 1; i <= value; i++) {
            result = (result + dfs(count - 1, value, sum - i, cache)) % 1000000007;
        }
        cache.put(key, result);
        return result;
    }
}


// method 2: DP(2D)
// time complexity: O(d * f * target), space complexity: O(d * target)
class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        if(d <= 0 || f <= 0 || target <= 0) {
            return 0;
        }
        int[][] dp = new int[d + 1][target + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= d; i++) {
            for(int j = 0; j <= target; j++) {
                for(int k = 1; k <= f; k++) {
                    if(j >= k) {
                        dp[i][j] = (dp[i][j] + dp[i-1][j-k]) % 1000000007;
                    }
                }
            }
        }
        return dp[d][target];
    }
}




