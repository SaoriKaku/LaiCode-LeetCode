
/*
You have an array of logs.  Each log is a space delimited string of words.
For each log, the first word in each log is an alphanumeric identifier.  Then, either:
Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
Return the final order of the logs.

Example 1:
Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 
Constraints:
0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.
*/


// method 1: sort
// time complexity: O(nlogn), space complexity: O(1)
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) {
            return new String[0];
        }
        Arrays.sort(logs, (a, b) -> {
            int firstSpaceIndexA = a.indexOf(' ');
            int firstSpaceIndexB = b.indexOf(' ');
            boolean flagA = Character.isDigit(a.charAt(firstSpaceIndexA + 1));
            boolean flagB = Character.isDigit(b.charAt(firstSpaceIndexB + 1));
            if(flagA && flagB) { // all number
                return 0;
            }
            else if(flagA && !flagB) { // a is number, b is letter
                return 1;
            }
            else if(!flagA && flagB) { // a is letter, b is number
                return -1;
            }
            else { // all letters
                int result = a.substring(firstSpaceIndexA + 1).compareTo(b.substring(firstSpaceIndexB + 1));
                if(result == 0) {
                    return a.substring(0, firstSpaceIndexA).compareTo(b.substring(0, firstSpaceIndexB));
                }
                return result;
            }
        });
        return logs;
    }
}

