
/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:
Input:  "69"
Output: true

Example 2:
Input:  "88"
Output: true

Example 3:
Input:  "962"
Output: false
*/


// method 1: HashMap
// time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean isStrobogrammatic(String num) {
        if(num == null || num.length() == 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('6', '9');
        map.put('9', '6');
        int left = 0, right = num.length() - 1;
        while(left <= right) {
            char leftChar = num.charAt(left);
            char rightChar = num.charAt(right);
            if(map.containsKey(leftChar) && map.get(leftChar) == rightChar) {
                left++;
                right--;
                continue;
            }
            return false;
        }
        return true;
    }
}



