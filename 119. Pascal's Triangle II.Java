
/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
Note that the row index starts from 0.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
*/


class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if(rowIndex == 0) {
            return result;
        }
        result.add(1);
        if(rowIndex == 1) {
            return result;
        }
        while(result.size() <= rowIndex) {
            int left = result.get(0);
            int size = result.size();
            for(int i = 1; i < size; i++) {
                int number = left + result.get(i);
                left = result.get(i);
                result.set(i, number);
            }
            result.add(1);
        }
        return result;
    }
}
