
/*
Given an array of integers nums, sort the array in ascending order.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
 
Constraints:
1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
*/


// method 1: quick sort
// time complexity: O(nlogn), space complexity: O(logn)
class Solution {
    // quick sort
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        quicksort(nums, 0, nums.length - 1);
        return nums;
    }
    public void quicksort(int[] nums, int left, int right) {
        if (left > right) return;
        int pivot = partition(nums, left, right);
        quicksort(nums, left, pivot - 1);
        quicksort(nums, pivot + 1, right);
    }
    public int partition(int[] nums, int left, int right) {
        int pivotIndex = (int)(Math.random() * (right - left)) + left;
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int leftBound = left, rightBound = right - 1;
        while(leftBound <= rightBound) {
            if(nums[leftBound] < pivotValue) {
                leftBound++;
            }
            else if(nums[rightBound] >= pivotValue) {
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


// method 2: merge sort
// time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        int[] helper = new int[nums.length];
        mergesort(nums, helper, 0, nums.length - 1);
        return nums;
    }
    public void mergesort(int[] nums, int[] helper, int left, int right) {
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mergesort(nums, helper, left, mid);
        mergesort(nums, helper, mid + 1, right);
        merge(nums, helper, left, mid, right);
    }
    public void merge(int[] nums, int[] helper, int left, int mid, int right) {
        for(int i = left; i <= right; i++) {
            helper[i] = nums[i];
        }
        int leftIndex = left, rightIndex = mid + 1;
        while(leftIndex <= mid && rightIndex <= right) {
            if(helper[leftIndex] < helper[rightIndex]) {
                nums[left++] = helper[leftIndex++];
            }
            else {
                nums[left++] = helper[rightIndex++];
            }
        }
        while(leftIndex <= mid) {
            nums[left++] = helper[leftIndex++];
        }
    }
}


// method 3: heap sort
// time complexity: O(n + nlogn), space complexity: O(1)
class Solution {
    public int[] sortArray(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return nums;
        }
        // heapify, make maxHeap
        int lastParent = (nums.length - 1 - 1) / 2;
        for(int i = lastParent; i >= 0; i--) {
            perlocateDown(nums, i, nums.length - 1);
        }
        // poll() max, and put max at the end of nums
        swap(nums, 0, nums.length - 1);
        for(int i = nums.length - 2; i > 0; i--) {
            perlocateDown(nums, 0, i);
            swap(nums, i, 0);
        }
        return nums;
    }
    // 如果孩子比较大，就把孩子换到上面来，直到当前节点大于最后一个母节点
    public void perlocateDown(int[] nums, int i, int lastIndex) {
        int lastParent = (lastIndex - 1) / 2;
        while(i <= lastParent) {
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;
            int swapCandidate = leftChild;
            // compare leftChild and rightChild, find bigger one
            if(rightChild <= lastIndex && nums[leftChild] < nums[rightChild]) {
                swapCandidate = rightChild;
            }
            // compare parent and candidate, bigger go up
            if(nums[i] < nums[swapCandidate]) {
                swap(nums, i, swapCandidate);
            }
            else {
                break;
            }
            i = swapCandidate;
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


