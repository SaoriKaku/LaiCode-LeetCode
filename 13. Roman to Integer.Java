
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

// time complexity: O(n), space complexity: O(n)
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        map1.put('I', 1);
        map1.put('V', 5);
        map1.put('X', 10);
        map1.put('L', 50);
        map1.put('C', 100);
        map1.put('D', 500);
        map1.put('M', 1000);
        HashMap<String, Integer> map2 = new HashMap<>();
        map2.put("IV", 4);
        map2.put("IX", 9);
        map2.put("XL", 40);
        map2.put("XC", 90);
        map2.put("CD", 400);
        map2.put("CM", 900);
        int i = 0;
        int result = 0;
        while(i < s.length()) {
            if(i + 1 < s.length()) {
                String curr = s.substring(i, i + 2);
                if(map2.containsKey(curr)) {
                    result += map2.get(curr);
                    i += 2;
                }
                else {
                    result += map1.get(s.charAt(i));
                    i++;
                }
            }
            else {
                result += map1.get(s.charAt(i));
                i++;
            }
        }
        return result;
    }
}




