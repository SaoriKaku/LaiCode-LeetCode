
/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
 

Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
*/


// method 1: for each word, search in the board
// board = m * n, words length = k, word length = l, time complexity: O(m * n * k * 4^l), space complexity: O(m * n + l)
class Solution {
    
    public int[][] pattern = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0) {
            return result;
        }
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for(String word: words) {
            if(helper(word, board, visited, row, col)) {
                result.add(word);
            }
        }
        return result;
    }
    
    public boolean helper(String word, char[][] board, boolean[][] visited, 
                          int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(dfs(word, 0, board, i, j, row, col, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public boolean dfs(String word, int index, char[][] board, int i, int j, 
                       int row, int col, boolean[][] visited) {
        if(index == word.length()) {
            return true;
        }
        if(i < 0 || i >= row || j < 0 || j >= col || visited[i][j]
           || board[i][j] != word.charAt(index)) {
            return false;
        }
        visited[i][j] = true;
        for(int k = 0; k < pattern.length; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            if(dfs(word, index + 1, board, nextRow, nextCol, row, col, visited)) {
                visited[i][j] = false;
                return true;
            } 
        }
        visited[i][j] = false;
        return false;
    }
}


/* method 2: trie + dfs, 
1. 根据board上面每一个字母，在trie上面找当前root的children里面是否有board[i][j]这个字符，如果有：
1.1 root = root.children[index]，root往下面继续走，
1.2 如果赋值后的root已经是一个词(isWord == true),就在result里面加入这个词，设置isWord = false，避免重复加入这个词
1.3 同时，board是往上下左右四个方向走，比如board往上走了，继续判断root.children里面有没有board[上]这个字符
1.4 总结：dfs(root, board[i][j]) ！！！root判断他的children里面有没有board[i][j]这个字符！！！
2. 复杂度分析：board = m * n, words length = k, word length = l, time complexity: O(m * n * 4^l), space complexity: O(l)
*/
class Solution {
    public class TrieNode {
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
    }
    public class Trie {
        public TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String input) {
            TrieNode currNode = root;
            for(char currChar: input.toCharArray()) {
                int index = currChar - 'a';
                if(currNode.children[index] == null) {
                    currNode.children[index] = new TrieNode();
                }
                currNode = currNode.children[index];
            }
            currNode.isWord = true;
        }
    }
    
    public int[][] pattern = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0) {
            return result;
        }
        int row = board.length;
        int col = board[0].length;
        Trie trie = new Trie();
        for(String word: words) {
            trie.insert(word);
        }
        StringBuilder curr = new StringBuilder();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                dfs(curr, board, i, j, trie.root, result);
            }
        }
        return result;
    }
    
    public void dfs(StringBuilder sb, char[][] board, int i, int j, TrieNode root, List<String> result) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*') {
            return;
        }
        char currChar = board[i][j];
        int index = currChar - 'a';
        if(root.children[index] == null) {
            return;
        }
        sb.append(currChar);
        root = root.children[index];
        if(root.isWord) {
            result.add(sb.toString());
            root.isWord = false;
        }
        board[i][j] = '*';
        for(int k = 0; k < pattern.length; k++) {
            int nextRow = i + pattern[k][0];
            int nextCol = j + pattern[k][1];
            dfs(sb, board, nextRow, nextCol, root, result);
            
        }
        board[i][j] = currChar;
        sb.deleteCharAt(sb.length() - 1);
    }
}




