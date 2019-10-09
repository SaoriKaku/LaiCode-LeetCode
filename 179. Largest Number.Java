
/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/


class Solution {
    public String largestNumber(int[] nums) {
        String[] number = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            number[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(number, new Comparator<String>() {
            public int compare(String a, String b) {
                String s1 = a + b;
                String s2 = b + a;
                return s2.compareTo(s1);
            }
        });
        //System.out.println(Arrays.toString(number));
        StringBuilder result = new StringBuilder();
        for(String curr: number) {
            result.append(curr);
        }
        if(number[0].equals("0")) {
            return "0";
        }
        return result.toString();
    }
}