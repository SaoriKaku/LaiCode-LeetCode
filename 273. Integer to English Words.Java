
/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:
Input: 123
Output: "One Hundred Twenty Three"

Example 2:
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
*/


// method 1: String
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    2147483648
    /1000000000, 2
    /1000000, 147
    /1000, 483
    /1, 648
    */
    public String numberToWords(int num) {
        if(num == 0) {
            return "Zero";
        }
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "One");
        map1.put(2, "Two");
        map1.put(3, "Three");
        map1.put(4, "Four");
        map1.put(5, "Five");
        map1.put(6, "Six");
        map1.put(7, "Seven");
        map1.put(8, "Eight");
        map1.put(9, "Nine");
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(2, "Twenty");
        map2.put(3, "Thirty");
        map2.put(4, "Forty");
        map2.put(5, "Fifty");
        map2.put(6, "Sixty");
        map2.put(7, "Seventy");
        map2.put(8, "Eighty");
        map2.put(9, "Ninety");
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(10, "Ten");
        map3.put(11, "Eleven");
        map3.put(12, "Twelve");
        map3.put(13, "Thirteen");
        map3.put(14, "Fourteen");
        map3.put(15, "Fifteen");
        map3.put(16, "Sixteen");
        map3.put(17, "Seventeen");
        map3.put(18, "Eighteen");
        map3.put(19, "Nineteen");
        StringBuilder result = new StringBuilder();
        while(num > 0) {
            if(num >= 1000000000) {
                int digit = num / 1000000000;
                result.append(map1.get(digit) + " Billion");
                num = num % 1000000000;
            }
            else if(num >= 1000000) {
                String curr = getThreeDigits(num / 1000000, map1, map2, map3);
                if(curr.length() > 0) {
                    result.append(" " + curr + " Million");
                }
                num = num % 1000000;
            }
            else if(num >= 1000) {
                String curr = getThreeDigits(num / 1000, map1, map2, map3);
                if(curr.length() > 0) {
                    result.append(" " + curr + " Thousand");
                }
                num = num % 1000;
            }
            else {
                String curr = getThreeDigits(num, map1, map2, map3);
                if(curr.length() > 0) {
                    result.append(" " + curr);
                }
                num = num % 1;
            }
        }
        return String.valueOf(result).trim();
    }
    public String getThreeDigits(int num, Map<Integer, String> map1, Map<Integer, String> map2, Map<Integer, String> map3) {
        StringBuilder result = new StringBuilder();
        while(num > 0) {
            if(num >= 100) {
                int digit = num / 100; 
                result.append(map1.get(digit) + " " + "Hundred");
                num = num % 100;
            }
            else if(num >= 20) {
                int digit = num / 10;
                result.append(" " + map2.get(digit));
                num = num % 10;
            }
            else if(num >= 10) {
                result.append(" " + map3.get(num));
                num = 0;
            }
            else {
                result.append(" " + map1.get(num));
                num = 0;
            }
        }
        return String.valueOf(result).trim();
    }
}


