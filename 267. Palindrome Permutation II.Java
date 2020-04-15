
/*
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:
Input: "aabb"
Output: ["abba", "baab"]

Example 2:
Input: "abc"
Output: []
*/


// method 1: HashMap + dfs
// time complexity: O(n + 128 + n * n!), space complexity: O(n)
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return result;
        }
        int[] map = new int[128];
        int count = 0;
        for(char curr: s.toCharArray()) {
            map[curr]++;
            if(map[curr] % 2 == 1) {
                count++;
            }
            else {
                count--;
            }
        }
        if(count <= 1) {
            char single = ' ';
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 128; i++) {
                if(map[i] > 0) {
                    for(int j = 0; j < map[i] / 2; j++) {
                        sb.append((char)i);
                    }
                    if(map[i] % 2 == 1) {
                        single = (char)i;
                    }
                }
            }
            dfs(0, String.valueOf(sb).toCharArray(), single, result);
        }
        return result;
    }
    public void dfs(int index, char[] input, char single, List<String> result) {
        if(index == input.length) {
            String solu = "";
            if(single == ' ') {
                solu = String.valueOf(input) + reverse(String.valueOf(input));
            }
            else {
                solu = String.valueOf(input) + single + reverse(String.valueOf(input));
            }
            result.add(solu);
            return;
        }
        Set<Character> set = new HashSet<>();
        for(int i = index; i < input.length; i++) {
            // we want to put charAt(i) at index, store all charAt(i) into a set
            // if charAt(i) already in set, there will be duplicate
            if(set.add(input[i])) {
                swap(input, index, i);
                dfs(index + 1, input, single, result);
                swap(input, index, i);
            }
        }
    }
    public String reverse(String s) {
        char[] input = s.toCharArray();
        int left = 0, right = input.length - 1;
        while(left < right) {
            swap(input, left++, right--);
        }
        return new String(input);
    }
    public void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}


