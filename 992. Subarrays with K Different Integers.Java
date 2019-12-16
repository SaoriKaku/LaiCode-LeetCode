
/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 
Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length
*/


// method 1: sliding window
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if(A == null || A.length == 0 || K == 0) {
            return 0;
        }
        return atMostK(A, K) - atMostK(A, K-1);
    }
    public int atMostK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int result = 0;
        while(right < A.length) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            while(map.size() > K) {
                map.put(A[left], map.get(A[left]) - 1);
                if(map.get(A[left]) == 0) {
                    map.remove(A[left]);
                }
                left++;
            }
            right++;
            result += right - left;
        }
        return result;
    }
}


