
/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

 
Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

 
Note:

The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
*/


/* method 1: HashMap + PriorityQueue
time complexity: the length of array: O(n), the maximum length of sentence: O(m)
method AutocompleteSystem: O(n)
method isPrefix: O(m)
method input: O(n * mlogn + 3logn)
space complexity: O(n)
*/ 
class AutocompleteSystem {
    
    public Map<String, Integer> map;
    public String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        prefix = "";
        for(int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#') {
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
            prefix = "";
            return result;
        }
        prefix += c;
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int res = entry2.getValue() - entry1.getValue();
                if(res == 0) {
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                return res;
            }
        });
        // time complexity: O(n * mlogn)
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(entry.getKey().equals(prefix) || isPrefix(entry.getKey(), prefix)) {
                maxHeap.offer(entry);
            }
        }
        int count = 3;
        // time compleixty: O(3logn)
        while(!maxHeap.isEmpty() && count > 0) {
            result.add(maxHeap.poll().getKey());
            count--;
        }
        return result;
    }
    // if b is prefix of a
    public boolean isPrefix(String a, String b) {
        if(a.length() < b.length()) {
            return false;
        }
        int i = 0;
        while(i < b.length()) {
            if(a.charAt(i) != b.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


/* method 2: Trie + PriorityQueue
time complexity: the length of array: O(n), the maximum length of sentence: O(m)
method AutocompleteSystem: O(n * m)
method insert: O(m)
method input: O(m + nlogn + 3logn), compare to O(n * mlogn + 3logn)
space complexity: O(n)
*/ 
class AutocompleteSystem {
    public class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> count;
        public TrieNode() {
            children = new HashMap<>();
            count = new HashMap<>();
        }
    }
    
    public class Trie{
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String input, int time) {
            TrieNode currNode = root;
            for(char ch: input.toCharArray()) {
                TrieNode nextNode = currNode.children.get(ch);
                if(nextNode == null) {
                    nextNode = new TrieNode();
                    currNode.children.put(ch, nextNode);
                }
                currNode = nextNode;
                currNode.count.put(input, currNode.count.getOrDefault(input, 0) + time);
            }// end for
        }// end insert
    }
    
    public Trie trie;
    public String prefix;
    //public int count = 0;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        prefix = "";
        for(int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        //System.out.println("count = " + count++);
        List<String> result = new ArrayList<>();
        if(c == '#') {
            trie.insert(prefix, 1);
            prefix = "";
            //System.out.println("#");
            return result;
        }
        prefix += c;
        // time complexity: O(m)
        TrieNode currNode = trie.root;
        for(char ch: prefix.toCharArray()) {
            if(currNode.children.get(ch) == null) {
                //System.out.println("no result");
                return result;
            }
            currNode = currNode.children.get(ch);
        }
        Map<String, Integer> map = currNode.count;
        //System.out.println("map: " + map);
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int res = entry2.getValue() - entry1.getValue();
                if(res == 0) {
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                return res;
            }
        });
        // time complexity: O(nlogn + 3logn)
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            maxHeap.offer(entry);
        }
        int count = 3;
        while(!maxHeap.isEmpty() && count > 0) {
            result.add(maxHeap.poll().getKey());
            count--;
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

