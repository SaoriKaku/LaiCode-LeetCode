
/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
*/


// method 1: hashmap + doubly linked list
// time complexity: O(1), space complexity: O(n)
class AllOne {
    /*
    count: 1 -> 2 -> 3 -> 4
    */
    class Node {
        int val;
        Node prev;
        Node next;
        Set<String> keySet;
        public Node(int val) {
            this.val = val;
            keySet = new HashSet<>();
        }
    }
    private Node dummy;
    private Map<String, Node> map;

    /** Initialize your data structure here. */
    public AllOne() {
        dummy = new Node(Integer.MIN_VALUE);
        dummy.next = dummy;
        dummy.prev = dummy;
        map = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(map.containsKey(key)) { // update value += 1
            Node currNode = map.get(key);
            int val = currNode.val;
            if(currNode.next.val != val + 1) { // create a new node
                insertAfterNode(currNode, key, val + 1);
            }
            else { // add key to node
                Node nextNode = currNode.next;
                nextNode.keySet.add(key);
            }
            // update map
            map.put(key, currNode.next);
            // update node 
            currNode.keySet.remove(key);
            if(currNode.keySet.size() == 0) {
                removeNode(currNode);
            }
        }
        else { // add key to node, where node.val = 1
            if(dummy.next.val == 1) {
                dummy.next.keySet.add(key);
            }
            else { // create node where node.val = 1
                insertAfterNode(dummy, key, 1);
            }
            // update map
            map.put(key, dummy.next);
        }
    }
    
    public void insertAfterNode(Node currNode, String key, int val) {
        Node newNode = new Node(val);
        Node nextNode = currNode.next;
        newNode.keySet.add(key);
        currNode.next = newNode;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        newNode.prev = currNode;
    }
    public void removeNode(Node currNode) {
        Node prevNode = currNode.prev;
        Node nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        currNode.next = null;
        currNode.prev = null;
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) {
            return;
        }
        Node currNode = map.get(key);
        int val = currNode.val;
        currNode.keySet.remove(key);
        if(val == 1) { // remove from keyset
            map.remove(key);
        }
        else { // remove from currNode, add it to prevNode
            if(currNode.prev.val != val - 1) {
                insertAfterNode(currNode.prev, key, val - 1);
            }
            else {
                Node prevNode = currNode.prev;
                prevNode.keySet.add(key);
            }
            map.put(key, currNode.prev);
        }
        if(currNode.keySet.size() == 0) {
            removeNode(currNode);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(dummy.prev == dummy) {
            return "";
        }
        Set<String> set = dummy.prev.keySet;
        //System.out.println("getMapKey: " + set);
        return set.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(dummy.next == dummy) {
            return "";
        }
        Set<String> set = dummy.next.keySet;
        //System.out.println("getMinKey: " + set);
        return set.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */





