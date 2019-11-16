
/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/


// method 1: use two stacks: stack and min, min to store current min for each element in stack
class MinStack {
    
    Deque<Integer> stack;
    Deque<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        min = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(min.isEmpty() || x < min.peek()) {
            min.push(x);
        }
        else {
            min.push(min.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        min.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


// method 2: use two stacks: stack and min, 
// technique for min: when new value <= min.peek(), push into min; 
// when stack.pop() == min.peek(), pop min
class MinStack {
    public Deque<Integer> stack;
    public Deque<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        min = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(min.isEmpty() || x <= min.peek()) {
             min.push(x);
        }
    }
    
    public void pop() {
        if(stack.isEmpty()) {
            return;
        }
        if(min.peek().equals(stack.peek())) {
            min.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
