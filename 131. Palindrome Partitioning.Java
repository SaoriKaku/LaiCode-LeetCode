
/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example:
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/


// method 1: dfs
// time complexity: O(n!), space complexity: O(n)
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return result;
        }
        List<String> curr = new ArrayList<>();
        dfs(s, 0, curr, result);
        return result;
    }
    /*
                  0
           /      |      \ 
         01=a     02=aa   03aab
       /   \      |
     12=a  13=ab  23=b
     /
    23=b
    */
    public void dfs(String s, int index, List<String> curr, List<List<String>> result) {
        if(index == s.length()) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for(int i = index; i < s.length(); i++) {
            if(isPalindrome(s, index, i)) {
                curr.add(s.substring(index, i + 1));
                dfs(s, i + 1, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}




