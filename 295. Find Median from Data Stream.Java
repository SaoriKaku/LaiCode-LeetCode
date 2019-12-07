
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.

Example:
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 
Follow up:
If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/


// method 1: two Heap
// time complexity: O(log(n/2)), space complexity: O(n)
class MedianFinder {
    /** initialize your data structure here. */
    /*
    minHeap: store the max half part
    maxHeap: store the min half part
    1. add a number
    1.1 add num to minHeap first
    1.2 add minHeap.poll to maxHeap
    // minHeap is always one size bigger than maxHeap
    1.3 if minHeap.size() < maxHeap.size()
    minHeap.offer(maxHeap.poll());
    2. return median
    if((minHeap + maxHeap) % 2 == 1) return minHeap.peek();
    if((minHeap + maxHeap) % 2 == 1) return (minHeap.peek() + maxHeap.peek()) / 2;
    */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
        if(minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        // System.out.println("minHeap.size = " + minHeap.size());
        // System.out.println("minHeap: " + minHeap);
        // System.out.println("maxHeap.size = " + maxHeap.size());
        // System.out.println("maxHeap: " + maxHeap);
        int sum = minHeap.size() + maxHeap.size();
        if(sum % 2 == 1) {
            return (double)(minHeap.peek());
        }
        return ((double)(minHeap.peek()) + maxHeap.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */



