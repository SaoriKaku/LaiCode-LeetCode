
/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/


class WordDictionary {
    
    public class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        int numWord = 0;
    }
    public TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr.numWord++;
            curr = curr.children[index];
        }
        curr.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        boolean[] result = new boolean[1];
        dfsHelper(word, 0, root, result);
        return result[0];
    }
    public void dfsHelper(String word, int index, TrieNode currNode, boolean[] result) {
        if(currNode == null) {
            return;
        }
        if(index == word.length()) {
            if(currNode.isWord) {
                result[0] = true;
            }
            return;
        }
        char currChar = word.charAt(index);
        if(currChar == '.') {
            for(int i = 0; i < 26; i++) {
                dfsHelper(word, index + 1, currNode.children[i], result);
            }
        }
        else {
            int pos = currChar - 'a';
            dfsHelper(word, index + 1, currNode.children[pos], result);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
