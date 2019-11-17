
/*
You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.

Implement the DinnerPlates class:

DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks are empty.
int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that stack, and returns -1 if the stack with that given index is empty.
Example:

Input: 
["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
Output: 
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

Explanation: 
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:     4
                                                       1  3  5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
                                                        1  3  5
                                                        ﹈ ﹈ ﹈ 
D.pop()            // Returns 5.  The stacks are now:      4
                                                        1  3 
                                                        ﹈ ﹈  
D.pop()            // Returns 4.  The stacks are now:   1  3 
                                                        ﹈ ﹈   
D.pop()            // Returns 3.  The stacks are now:   1 
                                                        ﹈   
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.
 

Constraints:

1 <= capacity <= 20000
1 <= val <= 20000
0 <= index <= 100000
At most 200000 calls will be made to push, pop, and popAtStack.
*/


// method 1: idea, only create stack, do not remove stack from map even if stack is empty
class DinnerPlates {
    public HashMap<Integer, Deque<Integer>> map;
    public int capacity;
    public int left; // the key in map for where to push 
    public int right; // the key in map for where to pop

    public DinnerPlates(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        left = 0;
        right = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        map.put(0, stack);
    }
   
    public void push(int val) {
        while(map.containsKey(left) && map.get(left).size() == capacity) {
            left++;
        }
        if(!map.containsKey(left)) {
            Deque<Integer> curr = new ArrayDeque<>();
            map.put(left, curr);
        }
        map.get(left).push(val);
        right = Math.max(left, right);
    }
    
    public int pop() {
        if(right == 0 && map.get(0).isEmpty()) {
            return -1;
        }
        while(right >= 0 && map.get(right).isEmpty()) {
            right--;
        }
        left = Math.min(left, right);
        return map.get(right).pop();
    }
    
    public int popAtStack(int index) {
        if(!map.containsKey(index) || map.get(index).isEmpty()) {
            return -1;
        }
        left = Math.min(left, index);
        return map.get(index).pop();
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */


