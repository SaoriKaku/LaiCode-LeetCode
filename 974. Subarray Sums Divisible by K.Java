
/*
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

Example 1:
Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 
Note:
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
*/


// method 1: HashMap + prefixSum
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        if(A == null || A.length == 0) {
            return 0;
        }
        // key: sum, value: the count of sum
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int result = 0, currSum = 0;
        for(int i = 0; i < A.length; i++) {
            currSum += A[i];
            if(K != 0) {
                currSum %= K;
                /* 
                k = 5, -3 need to find 3 or -2, 2 also need to find 3 or -2
                to -3 need to be 2, -3 + 5 = 2
                */ 
                if(currSum < 0) {
                    currSum += K; 
                }
            }
            if(map.containsKey(currSum)) {
                result += map.get(currSum);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return result;
    }
}

