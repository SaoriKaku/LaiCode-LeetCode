
/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
*/


// method 1: maxHeap + sliding window
// time complexity: O(n * k), remove element in heap is O(k), space complexity: O(k)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums== null || nums.length == 0 || k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if(a == b) {
                    return 0;
                }
                return b - a;
            } 
        });
        int[] result = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            if(i < k - 1) {
                maxHeap.offer(nums[i]);
            }
            else if(i == k - 1) {
                maxHeap.offer(nums[i]);
                result[0] = maxHeap.peek();
            }
            // 0 1  2  3 4 5 6 7 
            //[1,3,-1,-3,5,3,6,7]
            else {
                maxHeap.remove(nums[i - k]);
                maxHeap.offer(nums[i]);
                result[i - k + 1] = maxHeap.peek();
            }
        }
        return result;
    }
}


// method 2: deque + sliding window, nums[i] only go in and out from deque once.
// time compleixty: O(n), space complexity: O(n)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums== null || nums.length == 0 || k == 0) {
            return new int[]{};
        }
        // big -> small
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for(int i = 0; i < nums.length; i++) {
            if(i < k - 1) {
                updateDeque(deque, nums[i]);
            }
            else if(i == k - 1) {
                updateDeque(deque, nums[i]);
                result[0] = deque.peekFirst();
            }
            // 0 1  2  3 4 5 6 7 
            //[1,3,-1,-3,5,3,6,7]
            else {
                if(nums[i - k] == deque.peekFirst()) {
                    deque.pollFirst();
                }
                updateDeque(deque, nums[i]);
                result[i - k + 1] = deque.peekFirst();
            }
        }
        return result;
    }
    public void updateDeque(Deque<Integer> deque, int num) {
        while(!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offerLast(num);
    }
}




