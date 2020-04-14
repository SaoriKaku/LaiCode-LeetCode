
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:
Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/


// method 1: dfs, n = maxLen - minLen, l = length of number
// time complexity: O(n * 5^l), space complexity: O(l)
class Solution {
    public char[][] dict = new char[][] {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
    public int strobogrammaticInRange(String low, String high) {
        long minVal = Long.valueOf(low), maxVal = Long.valueOf(high);
        int minLen = low.length(), maxLen = high.length();
        int[] count = new int[1];
        for(int i = minLen; i <= maxLen; i++) {
            char[] curr = new char[i];
            dfs(0, i - 1, minVal, maxVal, curr, count);
        }
        return count[0];
    }
    public void dfs(int left, int right, long min, long max, char[] curr, int[] count) {
        if(left == right) {
            for(int i = 0; i <= 2; i++) {
                curr[left] = dict[i][0];
                long num = Long.valueOf(String.valueOf(curr));
                if(num >= min && num <= max) {
                    count[0]++;
                }
            }
            return;
        }
        if(left > right) {
            long num = Long.valueOf(String.valueOf(curr));
            if(num >= min && num <= max) {
                count[0]++;
            }
            return;
        }
        if(left > 0) {
            curr[left] = '0';
            curr[right] = '0';
            dfs(left + 1, right - 1, min, max, curr, count);
        }
        for(int i = 1; i < dict.length; i++) {
            curr[left] = dict[i][0];
            curr[right] = dict[i][1];
            dfs(left + 1, right - 1, min, max, curr, count);
        }
    }
}
