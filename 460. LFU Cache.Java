
/*
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.

Follow up: Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache(2 = capacity);
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/


// method 1: doubly linked list + HashMap
// time complexity: O(1), space complexity: O(n)
class LFUCache {
    class Node {
        int key, value;
        int freq;
        Node next, prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }
    class NodeList {
        Node dummy;
        int size;
        public NodeList() {
            dummy = new Node(-1, -1);
            dummy.next = dummy;
            dummy.prev = dummy;
            size = 0;
        }
        public void removeNode(Node node) {
            // prevNode -> node -> nextNode
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
        public void addHead(Node newHead) {
            Node dummy = this.dummy;
            // dummy -> newHead -> nextNode
            Node nextNode = dummy.next;
            dummy.next = newHead;
            newHead.next = nextNode;
            nextNode.prev = newHead;
            newHead.prev = dummy;
            size++;
        }
    }
    // node map<key: key, value: one node>
    Map<Integer, Node> nodeMap;
    // times map<key: freq, value: node list>
    Map<Integer, NodeList> freqMap;
    int minFreq;
    int capacity;
    int size;
    public LFUCache(int capacity) {
        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(nodeMap.containsKey(key)) {
            Node currNode = nodeMap.get(key);
            // remove node from curr list
            NodeList currList = freqMap.get(currNode.freq);
            currList.removeNode(currNode);
            // update minFreq
            if(currNode.freq == minFreq && currList.size == 0) {
                minFreq = minFreq + 1;
            }
            // add freq to currNode
            currNode.freq++;
            // add node to next list
            freqMap.putIfAbsent(currNode.freq, new NodeList());
            freqMap.get(currNode.freq).addHead(currNode);
            return currNode.value;
        }
        return -1;
    }
    
    /* 
       add a node, the node freq = minFreq = 1
       change a node's value, freq++
    */
    public void put(int key, int value) {
        if(capacity == 0) return;
        // remove old node and build new node
        if(nodeMap.containsKey(key)) {
            Node currNode = nodeMap.get(key);
            currNode.value = value;
            // remove node from freqMap(freq)
            NodeList currList = freqMap.get(currNode.freq);
            currList.removeNode(currNode);
            // update minFreq++
            if(currNode.freq == minFreq && currList.size == 0) {
                minFreq = minFreq + 1;
            }
            // add node to freqMap(freq++)
            currNode.freq++;
            freqMap.putIfAbsent(currNode.freq, new NodeList());
            freqMap.get(currNode.freq).addHead(currNode);
        }
        else { // remove the tail in least freq list
            if(nodeMap.size() == capacity) {
                // remove node from freqMap
                NodeList minList = freqMap.get(minFreq);
                Node tailNode = minList.dummy.prev;
                minList.removeNode(tailNode);
                // remove node from nodeMap
                nodeMap.remove(tailNode.key);
            }
            // add new node to nodeMap
            Node newNode = new Node(key, value);
            nodeMap.put(key, newNode);
            // add new ndoe to freqMap
            freqMap.putIfAbsent(1, new NodeList());
            freqMap.get(1).addHead(newNode);
            minFreq = 1;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */




