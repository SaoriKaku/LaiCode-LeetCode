
/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/


// method 1: list
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if(numRows == 0) {
            return result;
        }
        List<Integer> prevList = new ArrayList<>();
        prevList.add(1);
        result.add(prevList);
        while(result.size() < numRows) {
            List<Integer> currList = new ArrayList<>();
            currList.add(1);
            for(int i = 1; i < prevList.size(); i++) {
                int number = prevList.get(i-1) + prevList.get(i);
                currList.add(number);
            }
            currList.add(1);
            result.add(currList);
            prevList = currList;
        }
        return result;
    }
}


