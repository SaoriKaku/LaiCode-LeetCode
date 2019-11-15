
/*
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.

 
Follow up:
Could you solve it using only O(1) extra space?

 
Example 1:

Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 

Example 2:

Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
 

Example 3:

Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
 

Note:

All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.
*/


// method 1: two pointers, inplace, require O(1) space complexity
// time complexity: O(n), space complexity: O(n) -> depends on how big "count" is.
class Solution {
    public int compress(char[] chars) {
        if(chars == null || chars.length == 0) {
            return 0;
        }
        int fast = 0;
        int slow = 0;
        int count = 0;
        // return slow;
        while(fast < chars.length) {
            count = 0;
            char curr = chars[fast];
            while(fast < chars.length && chars[fast] == curr) {
                fast++;
                count++;
            }
            chars[slow++] = curr;
            if(count > 1 && count < 10) {
                chars[slow++] = (char)(count + '0');
            }
            else if(count >= 10){
                // String.valueOf(count) = Integer.toString(count)
                for(char digit: String.valueOf(count).toCharArray()) {
                    chars[slow++] = digit;
                }
            }
        }
        return slow;
    }
}



