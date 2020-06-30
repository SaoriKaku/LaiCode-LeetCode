
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5

Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

// method 1: maxHeap
// time complexity: O(nlogn + klogn), space complexity: O(n)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int curr: nums) {
            maxHeap.offer(curr);
        }
        for(int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }
}


// method 2: minHeap
// time complexity: O(nlogk)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for(int i = 0; i < nums.length; i++) {
            if(i < k) {
                minHeap.offer(nums[i]);
                continue;
            }
            if(nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }
}


// method 3: quick select(ascending)
// time complexity: O(n), space complexity: O(1)
class Solution {
  public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int randomIndex = partition(nums, left, right);
            if(randomIndex == nums.length - k) {
                return nums[randomIndex];
            }
            else if(randomIndex < nums.length - k) {
                left = randomIndex + 1;
            }
            else {
                right = randomIndex - 1;
            }
        }
        return nums[left];
    }
    
    public int partition(int[] nums, int left, int right) {
        int randomIndex = getRandomIndex(left, right);
        int pivot = nums[randomIndex];
        swap(nums, randomIndex, right);
        int leftBound = left;
        int rightBound = right;
        while(leftBound <= rightBound) {
            if(nums[leftBound] < pivot) {
                leftBound++;
            }
            else if(nums[rightBound] >= pivot) {
                rightBound--;
            }
            else {
                swap(nums, leftBound, rightBound);
                leftBound++;
                rightBound--;
            }
        }
        swap(nums, leftBound, right);
        return leftBound;
    }
    
    public int getRandomIndex(int left, int right) {
        int temp = left + (int)(Math.random() * (right - left + 1));
        return temp;
    }
    
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


// method 4: quick select(descending)
// time complexity: O(n), space complexity: O(1)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int pivotIndex = partition(nums, left, right);
            //System.out.println(Arrays.toString(nums));
            if(pivotIndex == k - 1) {
                return nums[pivotIndex];
            }
            else if(pivotIndex < k - 1) {
                left = pivotIndex + 1;
            }
            else {
                right = pivotIndex - 1;
            }
        }
        return nums[left];
    }
    public int partition(int[] nums, int left, int right) {
        int leftBound = left, rightBound = right - 1;
        int target = nums[right];
        while(leftBound <= rightBound) {
            if(nums[leftBound] > target) {
                leftBound++;
            }
            else if(nums[rightBound] <= target) {
                rightBound--;
            }
            else {
                swap(nums, leftBound++, rightBound--);
            }
        }
        swap(nums, leftBound, right);
        return leftBound;
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}





