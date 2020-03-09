
/*
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

Example 1:
Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").

Example 2:
Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 
Constraints:
1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.
*/


// method 1: array + sort + binary search
// queries: length n1, maxLength of query: m1; words: length n2, maxLength of word: m2
// time complexity: O(n1 * m1 + n2 * m2 + n2 * log(n2) + n1 * log(n2)), space complexity: O(n1 + n2)
class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        int[] queryCount = getCountArray(queries);
        int[] wordCount = getCountArray(words);
        Arrays.sort(wordCount);
        //System.out.println("queryCount: " + Arrays.toString(queryCount));
        //System.out.println("wordCountt: " + Arrays.toString(wordCount));
        for(int i = 0; i < queries.length; i++) {
            int index = searchFirstIndexBiggerThanTarget(wordCount, queryCount[i]);
            //System.out.println("word = " + queries[i] + ", index = " + index);
            if(index == -1) {
                result[i] = 0;
            }
            else {
                result[i] = wordCount.length - index;
            }
        }
        return result;
    }
    public int searchFirstIndexBiggerThanTarget(int[] wordCount, int target) {
        int left = 0, right = wordCount.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(wordCount[mid] == target) {
                while(mid + 1 < wordCount.length && wordCount[mid + 1] == wordCount[mid]) {
                    mid++;
                }
                return wordCount[mid] > target ? mid : mid + 1;
            }
            else if(wordCount[mid] < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        //System.out.println("left = " + wordCount[left] + ", target = " + target);
        if(wordCount[left] > target) return left;
        if(wordCount[right] > target) return right;
        return -1;
    }
    public int[] getCountArray(String[] input) {
        int[] result = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            char minChar = 'z';
            int count = 0;
            for(int j = 0; j < input[i].length(); j++) {
                if(input[i].charAt(j) < minChar) {
                    minChar = input[i].charAt(j);
                    count = 1;
                }
                else if(input[i].charAt(j) == minChar) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}


