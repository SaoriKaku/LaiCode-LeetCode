
/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

// method 1: two pointers from beginning
// time complexity: O(m + n), space complexity: O(m)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2 == null || n == 0) {
            return;
        }
        int[] temp = new int[m];
        for(int i = 0; i < m; i++) {
            temp[i] = nums1[i];
        }
        int i = 0;
        int j = 0;
        int k = 0;
        while(k < m && j < n) {
            if(temp[k] < nums2[j]) {
                nums1[i] = temp[k++];
                
            }
            else {
                nums1[i] = nums2[j++];
            }
            i++;
        }
        while(k < m) {
            nums1[i++] = temp[k++];
        }
        while(j < n) {
            nums1[i++] = nums2[j++];
        }
    }
}


// method 2: two pointers from end 
// time complexity: O(m + n), space complexity: O(1)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2 == null || n == 0) {
            return;
        }
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(i >= 0 && j >= 0) {
            if(nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }
            else {
                nums1[k--] = nums2[j--];
            }
        }
        while(i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}


