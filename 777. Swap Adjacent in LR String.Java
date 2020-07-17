
/*
In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example:
Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
 
Constraints:
1 <= len(start) == len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.
*/


// method 1: order
// time complexity: O(n), space complexity: O(1)
class Solution {
    public boolean canTransform(String start, String end) {
        if(start == null || end == null || start.length() != end.length()) {
            return false;
        }
        int n = start.length();
        if(!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }
        int i1 = 0, i2 = 0;
        while(i1 < n) {
            while(i1 < n && start.charAt(i1) == 'X') {
                i1++;
            }
            while(i2 < n && end.charAt(i2) == 'X') {
                i2++;
            }
            if(i1 == n) {
                return true;
            }
            char curr1 = start.charAt(i1);
            char curr2 = end.charAt(i2);
            if(curr1 != curr2) {
                return false;
            }
            // L could be moved to left
            if(curr1 == 'L' && i1 < i2) {
                return false;
            }
            // R could be moved to right
            if(curr1 == 'R' && i1 > i2) {
                return false;
            }
            i1++;
            i2++;
        }
        return true;
    }
}


