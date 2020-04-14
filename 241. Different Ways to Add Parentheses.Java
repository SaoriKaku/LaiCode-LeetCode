
/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:
Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2

Example 2:
Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
*/


   /*
    2*3-4*5
    6, -1, 20
    (6-4)*5=10
    2*-1*5=-10
    2*-1*5=-10
    2*3-20=-14
    2*(3-20)=-34
    */
// method 1: dfs with memorization
// time complexity: O(?), space complexity: O(?)
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>();
        return helper(input, map);
    }
    public List<Integer> helper(String input, Map<String, List<Integer>> map) {
        if(map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < input.length(); i++) {
            char sign = input.charAt(i);
            if(sign == '+' || sign == '-' || sign == '*') {
                String leftStr = input.substring(0, i);
                String rightStr = input.substring(i+1);
                List<Integer> leftList = helper(leftStr, map);
                List<Integer> rightList = helper(rightStr, map);
                for(int left: leftList) {
                    for(int right: rightList) {
                        if(sign == '+') {
                            result.add(left + right);
                        }
                        else if(sign == '-') {
                            result.add(left - right);
                        }
                        else if(sign == '*') {
                            result.add(left * right);
                        }
                    }
                } // end for
            } // end if
        } // end for
        if(result.size() == 0) {
            result.add(Integer.valueOf(input));
        }
        map.put(input, result);
        return result;
    }
}

