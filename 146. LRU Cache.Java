
/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/


class LRUCache {
    /*
        1. the number of elements is determined -> int size, int capacity
        2. order matters -> array or linkedlist -> linkedlist
        3. Hashtable -> HashSet or HashMap -> HashMap<Key = input key, value = reference to listNode>
    */
    private ListNode dummy;
	private static HashMap<Integer, ListNode> map; 
	private int size;
	private int capacity;
	
	class ListNode {
	    ListNode prev;
	    ListNode next;
	    int key;
	    int value;
	    public ListNode (int key, int value) {
	    	this.key = key;
	        this.value = value;
	    }
	}
	
	public LRUCache(int capacity) {
	    map = new HashMap<>();
	    this.capacity = capacity;
	    size = 0;
	    dummy = new ListNode(0, 0);
	    dummy.next = dummy;
	    dummy.prev = dummy;
	}
	
	public void removeNode(ListNode curr) {
	    curr.prev.next = curr.next;
	    curr.next.prev = curr.prev;
	    size--;
	    if(size == 0) {
	        dummy.next = dummy;
	        dummy.prev = dummy;
	    }
	    curr.prev = null;
	    curr.next = null;
	}
	
	public void addHead(ListNode curr) {
	    if(size == 0) {
	        curr.next = dummy;
	        curr.prev = dummy;
	        dummy.next = curr;
	        dummy.prev = curr;
	    }
	    else {
	    	curr.prev = dummy;
	    	curr.next = dummy.next;
	    	dummy.next.prev = curr;
	        dummy.next = curr;
	    }
	    size++;
	}
	
	/*
	whether it is in map,
	1. if so, move it to head and return it value
	2. otherwise, return -1
	*/
	public int get(int key) {
	    if(map.containsKey(key)) {
	        ListNode curr = map.get(key);
	        removeNode(curr);
	        addHead(curr);
	        return curr.value;
	    }
	    return -1;
	}
	
	/*
	check whether it is in map,
	1. if so, move it to head
	2. otherwise, check the cache size
	(1) if cache size > capacity, remove tail, remove tail from map, size--, add new Node in map, insert to head, size++;
	(2) otherwise, insert it to the head and put it in map, size++
	*/
	public void put(int key, int value) {
	    if(map.containsKey(key)) {
	        ListNode curr = map.get(key);
            // !!!key point
            curr.value = value;
	        removeNode(curr);
	        addHead(curr);
	    }
	    else {
	        ListNode newNode = new ListNode(key, value);
	        if(size == capacity) {
	        	map.remove(dummy.prev.key);
	            removeNode(dummy.prev);         
	        }
	        map.put(key, newNode);
	        addHead(newNode);
	    }
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */




