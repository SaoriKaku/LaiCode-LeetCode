
/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
*/


// method 1: words.length == n, word.length() == m
// time complexity: O(n * m^2), space complexity: O(n)
class Solution {
    /*
    把每个词的放入一个set里面
    1. 先找长度相同的，把当前词取反，然后看set里面有没有
    2. 从前面找i，如果[0, i]是palindrome，就把[i+1, end]取反，看set里面有没有
    把一个词分为 [1, l-1], [2, l-2], [3, l-3]...[l-1, 1]，前面这部分必须是parlindrome
    3. 从后面找i，如果[i, end]是palindrome，就把[0, i-1]取反，看set里面有没有
    */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if(words == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            for(int j = 0; j <= word.length(); j++) {
                String first = word.substring(0, j);
                String reverseFirst = reverse(first);
                String last = word.substring(j);
                String reverseLast = reverse(last);
                if(isPalindrome(first)) {
                    if(map.containsKey(reverseLast) && map.get(reverseLast) != i) {
                        List<Integer> solu = new ArrayList<>();
                        solu.add(map.get(reverseLast));
                        solu.add(i);
                        result.add(solu);
                    }
                }
                if(isPalindrome(last)) {
                    if(map.containsKey(reverseFirst) && map.get(reverseFirst) != i && last.length() != 0) {
                        List<Integer> solu = new ArrayList<>();
                        solu.add(i);
                        solu.add(map.get(reverseFirst));
                        result.add(solu);
                    }
                }
            }
        }
        return result;
    }
    
    public String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
    
    public boolean isPalindrome(String input) {
        if(input.length() <= 1) return true;
        int i = 0;
        int j = input.length() - 1;
        while(i < j) {
            if(input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}


