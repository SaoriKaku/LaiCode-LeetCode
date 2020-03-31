
/*
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:
Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2
For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.
00 - 0
10 - 2
11 - 3
01 - 1

Example 2:
Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].
*/


class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if(n == 0) {
            result.add(0);
            return result;
        }
        List<String> prev = new ArrayList<>();
        prev.add("0");
        prev.add("1");
        for(int i = 0; i < n-1; i++) {
            List<String> curr = new ArrayList<>();
            int size = prev.size();
            for(int j = 0; j < size; j++) {
                curr.add("0" + prev.get(j));
            }
            for(int j = 0; j < size; j++) {
                curr.add("1" + prev.get(size - 1 - j));
            }
            prev = curr;
        }
        for(String str: prev) {
            result.add(Integer.valueOf(str, 2));
        }
        return result;
    }
}



