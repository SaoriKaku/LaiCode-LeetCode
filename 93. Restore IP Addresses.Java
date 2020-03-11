
/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
*/


// method 1: dfs
// time complexity: O(3^4), space complexity: O(4)
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return result;
        }
        List<String> curr = new ArrayList<>();
        dfs(s, 0, curr, result);
        return result;
    }
    public void dfs(String s, int i, List<String> curr, List<String> result) {
        if(i > s.length() || curr.size() > 4) {
            return;
        }
        if(curr.size() == 4 && i == s.length()) {
            StringBuilder ip = new StringBuilder();
            for(int j = 0; j < 3; j++) {
                ip.append(curr.get(j));
                ip.append('.');
            }
            ip.append(curr.get(3));
            result.add(String.valueOf(ip));
            return;
        }
        if(i + 1 > s.length()) {
            return;
        }
        String number1 = s.substring(i, i + 1);
        curr.add(number1);
        dfs(s, i + 1, curr, result);
        curr.remove(curr.size() - 1);
        
        if(i + 2 > s.length()) {
            return;
        }
        String number2 = s.substring(i, i + 2);
        if(Integer.valueOf(number2) < 10) {
            return;
        }
        curr.add(number2);
        dfs(s, i + 2, curr, result);
        curr.remove(curr.size() - 1);
        
        if(i + 3 > s.length()) {
            return;
        }
        String number3 = s.substring(i, i + 3);
        if(Integer.valueOf(number3) > 255) {
            return;
        }
        curr.add(number3);
        dfs(s, i + 3, curr, result);
        curr.remove(curr.size() - 1);
    }
}


