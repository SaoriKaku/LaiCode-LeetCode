
/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:
Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Example 3:
Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Example 4:
Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]

Example 5:
Input: num = "3456237490", target = 9191
Output: []
*/


// method 1: dfs, l = num.length()
// time complexity: O(l!), space complexity: O(l)
class Solution {
    /* 
    We simply need to keep track of the last operand in our expression 
    for + -, prevTotal +/- curr
    for *, prevTotal - prev + prev * curr
    */ 
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", result);
        return result;
    }
    
    public void dfs(String num, int target, int index, long prevSum, long prevVal, String currPath, List<String> result) {
        if(index == num.length()) {
            if(prevSum == target) {
                result.add(currPath);
            }
            return;
        }
        for(int i = index; i < num.length(); i++) {
            // first digit = 0, and later digit = 0, not valid
            if(i > index && num.charAt(index) == '0') break;
            long currVal = Long.parseLong(num.substring(index, i + 1));
            if(index == 0) {
                dfs(num, target, i + 1, currVal, currVal, currPath + currVal, result);
            }
            else {
                // for sign +
                dfs(num, target, i + 1, prevSum + currVal, currVal, currPath + "+" + currVal, result);
                // for sign -
                dfs(num, target, i + 1, prevSum - currVal, -currVal, currPath + "-" + currVal, result);
                // for sign *
                dfs(num, target, i + 1, prevSum - prevVal + prevVal * currVal, prevVal * currVal, currPath + "*" + currVal, result);
            }
        }
    }
}




