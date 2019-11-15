
/*
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
*/


// method 1: replace or edit
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null || s.length() == 0 && t.length() == 0) {
            return false;
        }
        if(s.length() == t.length()) {
            return replace(s, t);
        }
        else if(s.length() == t.length() + 1) {
            return edit(s, t);
        }
        else if(s.length() == t.length() - 1) {
            return edit(t, s);
        }
        else {
            return false;
        }
    }
    public boolean replace(String a, String b) {
        int i = 0;
        int count = 0;
        while(i < a.length()) {
            if(a.charAt(i) != b.charAt(i)) {
                count++;
            }
            if(count > 1) {
                return false;
            }
            i++;
        }
        return count == 1;
    }
    // first is longer than second
    public boolean edit(String first, String second) {
        int i = 0;
        int j = 0;
        int count = 0;
        while(j < second.length()) {
            if(first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            }
            else {
                i++;
                count++;
                if(count > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}





