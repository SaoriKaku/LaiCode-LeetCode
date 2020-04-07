
/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.

Example 1:
Input: version1 = "0.1", version2 = "1.1"
Output: -1

Example 2:
Input: version1 = "1.0.1", version2 = "1"
Output: 1

Example 3:
Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1

Example 4:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”

Example 5:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 
Note:
Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
Version strings do not start or end with dots, and they will not be two consecutive dots.
*/


// method 1: array + split
// time complexity: O(m + n + max(m, n)), space complexity: O(m + n)
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] list1 = version1.split("\\.");
        String[] list2 = version2.split("\\.");
        int len1 = list1.length, len2 = list2.length;
        int len = Math.max(len1, len2);
        for(int i = 0; i < len; i++) {
            int v1 = i < len1 ? Integer.valueOf(list1[i]) : 0;
            int v2 = i < len2 ? Integer.valueOf(list2[i]) : 0;
            if(v1 != v2) {
                return v1 > v2 ? 1 : -1;
            }
        }
        return 0;
    }
}


// method 2: array + two pointers
// time complexity: O(max(m, n)), space complexity: O(1)
class Solution {
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length(), len2 = version2.length();
        int i = 0, j = 0;
        while(i < len1 || j < len2) {
            int[] result1 = getNumberAndIndex(version1, i);
            int num1 = result1[0];
            i = result1[1];
            int[] result2 = getNumberAndIndex(version2, j);
            int num2 = result2[0];
            j = result2[1];
            if(num1 != num2) {
                return num1 > num2 ? 1 : -1;
            }
        }
        return 0;
    }
    // return new int[] {number, index}
    private int[] getNumberAndIndex(String version, int index) {
        if(index >= version.length()) {
            return new int[] {0, index};
        }
        StringBuilder sb = new StringBuilder();
        int start = index;
        while(index < version.length() && version.charAt(index) != '.') {
            sb.append(version.charAt(index));
            index++;
        }
        return new int[] {Integer.valueOf(sb + ""), index + 1};
    }
}




