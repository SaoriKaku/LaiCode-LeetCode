
/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.
*/

// method 1: brute force
// time complexity: O(m * n), space complexity: O(n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[] {};
        }
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            result[i] = -1;
            for(int j = 1; j < nums.length; j++) {
                if(nums[(i + j) % nums.length] > nums[i]) {
                    result[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        return result;
    }
}


// method 2: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[] {};
        }
        int[] result = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        /*
        5 4 3 2 1，在上面这个例子，5 4 3 2 1 5 4 3 2 1
        遍历第一遍的时候，0 0 0 0 0 -1 -1 -1 -1 -1
        遍历第二遍的时候，-1 5 5 5 5 -1 -1 -1 -1 -1
        */
        for(int i = 2 * nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[i % nums.length] >= stack.peek()) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                result[i % nums.length] = -1;
            }
            else {
                result[i % nums.length] = stack.peek();
            }
            stack.push(nums[i % nums.length]);
            //System.out.println(Arrays.toString(result));
        }
        return result;
    }
}


// method 3: stack
// time complexity: O(n), space complexity: O(n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // store index
        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
            stack.push(i);
        }
        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                result[index] = nums[i];
            }
        }
        return result;
    }
}

