
/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
        q.push(start);
        step = 0;
        while not q.empty()
            step ++;
            size = q.size();
            while size-- > 0
                node = q.pop();
                newNode = expand(node);
                if goal in newNodes: return step + 1
                q.append(newNode);
        return not_found      
        */
        HashSet<String> set = new HashSet<>();
        for(String curr: wordList) {
            set.add(curr);
        }
        if(!set.contains(endWord)) {
            return 0;
        }
        int count = 1;
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for(int i = 0; i < size; i++) {
                char[] word = queue.poll().toCharArray();
                //System.out.println(String.valueOf(word));
                for(int j = 0; j < word.length; j++) {
                    char prev = word[j];
                    for(int k = 0; k < 26; k++) {
                        word[j] = (char)('a' + k);
                        //System.out.println("word[j] = " + word[j]);
                        String nextWord = String.valueOf(word);
                        //System.out.println("nextWord = " + nextWord);
                        if(nextWord.equals(endWord)) {
                            return count;
                        }
                        if(set.contains(nextWord)) {
                            queue.offer(nextWord);
                            set.remove(nextWord);
                        }
                    }
                    word[j] = prev;
                }
            }
        }
        return 0;
    }
}




