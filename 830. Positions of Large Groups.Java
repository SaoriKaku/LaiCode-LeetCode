
/*
In a string S of lowercase letters, these letters form consecutive groups of the same character.
For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
The final answer should be in lexicographic order.

Example 1:
Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.

Example 2:
Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.

Example 3:
Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
 
Note:  1 <= S.length <= 1000
*/


// method 1: two pinters
// time complexity: O(n), space complexity: O(1)
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return result;
        }
        int left = 0;
        int right = 1;
        char[] input = S.toCharArray();
        while(right < input.length) {
            if(input[right] == input[right - 1]) {
                while(right < input.length && input[right] == input[right - 1]) {
                    right++;
                }
                //System.out.println("left = " + left + ", right = " + right);
                if(right - left >= 3) {
                    List<Integer> curr = new ArrayList<>();
                    curr.add(left);
                    curr.add(right - 1);
                    result.add(curr);
                }
            }
            left = right;
            right++;
        }
        return result;
    }
}




