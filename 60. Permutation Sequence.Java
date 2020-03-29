
/*
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:
Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.

Example 1:
Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"
*/


// method 1: Math
// time complexity: O(n), space complexity: O(n)
/* factorial
    index 0 1 2
          1 1 2 6
    remainNumber
    index 0 1 2
          1 2 3 
    */
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> remainNumber = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            remainNumber.add(i);
        }
        int[] factorial = new int[n];
        factorial[0] = 1;
        for(int i = 1; i < n; i++) {
            factorial[i] = factorial[i-1] * i;
        }
        k -= 1;
        StringBuilder result = new StringBuilder();
        for(int i = n-1; i >= 0; i--) {
            int index = k / factorial[i];
            k -= index * factorial[i];
            result.append(remainNumber.get(index));
            remainNumber.remove(index);
        }
        return result.toString();
    }
}


