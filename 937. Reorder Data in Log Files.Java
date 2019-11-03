
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


// method 1: by myself, input size == n
// time complexity: O(nlogn), space complexity: O(nlogn)
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        /*
        1. split input into two ArrayList, one is letter logs, the other is digit logs
        2. sort the array containing letter logs
        3. combine them
        */
        if(logs == null || logs.length == 0) {
            return new String[] {};
        }
        List<String> letterLog = new ArrayList<>();
        List<String> digitLog = new ArrayList<>();
        for(int i = 0; i < logs.length; i++) {
            if(isLetterLog(logs[i])) {
                letterLog.add(logs[i]);
            }
            else {
                digitLog.add(logs[i]);
            }
        }
        Collections.sort(letterLog, new MyComparator());
        String[] result = new String[logs.length];
        for(int i = 0; i < result.length; i++) {
            if(i < letterLog.size()) {
                result[i] = letterLog.get(i);
            }
            else {
                // 0 - letterLog.size() - 1
                // i - i + letterLog.size()
                result[i] = digitLog.get(i - letterLog.size());
            }
        }
        return result;
    }
    
    class MyComparator implements Comparator<String> {
        public int compare(String a, String b) {
            int spaceIndexOfA = findSpaceIndex(a);
            int spaceIndexOfB = findSpaceIndex(b);
            int result = a.substring(spaceIndexOfA + 1).compareTo(b.substring(spaceIndexOfB + 1));
            if(result != 0) {
                return result;
            }
            return a.compareTo(b);
        }
    }
    
    public int findSpaceIndex(String input) {
        int spaceIndex = 0;
        while(spaceIndex < input.length()) {
            if(input.charAt(spaceIndex) == ' ') {
                break;
            }
            spaceIndex++;
        }
        return spaceIndex;
    }
    
    public boolean isLetterLog(String input) {
        int spaceIndex = findSpaceIndex(input);
        return Character.isLetter(input.charAt(spaceIndex + 1));
    }
}


// method 2: from others, input size == n
// time complexity: O(nlogn), space complexity: O(nlogn)
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        /*
        1. split input into two ArrayList, one is letter logs, the other is digit logs
        2. sort the array containing letter logs
        3. combine them
        */
        if(logs == null || logs.length == 0) {
            return new String[] {};
        }
        Arrays.sort(logs, new MyComparator());
        return logs;
    }
    
    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            int spaceIndexOfA = a.indexOf(' ');
            int spaceIndexOfB = b.indexOf(' ');
            char nextCharOfA = a.charAt(spaceIndexOfA + 1);
            char nextCharOfB = b.charAt(spaceIndexOfB + 1);
            if(Character.isDigit(nextCharOfA) && Character.isDigit(nextCharOfB)) {
                return 0;
            }
            else if(Character.isLetter(nextCharOfA) && Character.isLetter(nextCharOfB)) {
                int result = a.substring(spaceIndexOfA + 1).compareTo(b.substring(spaceIndexOfB + 1));
                if(result == 0) {
                    return a.compareTo(b);
                }
                return result;
            }
            else {
                return Character.isLetter(nextCharOfA) ? -1 : 1;
            }
        }
    }

}


