
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
*/


// time complexity: O(min(m, n)), space complexity: O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int sum = nums1.length + nums2.length;
        int midIndex = (sum + 1) / 2;
        int left = 0;
        int right = nums1.length;
        while(left < right) {
            int mid1 = left + (right - left) / 2;
            int mid2 = midIndex - mid1;
            // mid1 - 1 < mid1
            // mid2 - 1 < mid2
            if(nums1[mid1] < nums2[mid2 - 1]) {
                left = mid1 + 1;
            }
            else {
                right = mid1;
            }
        }
        int mid1 = left;
        int mid2 = midIndex - left;
        // min(mid1 - 1, mid2 - 1)
        int median1 = Math.max(mid1 <= 0 ? Integer.MIN_VALUE : nums1[mid1 - 1], mid2 <= 0 ? Integer.MIN_VALUE : nums2[mid2 - 1]);
        if(sum % 2 == 1) {
            return median1 * 1.0;
        }
        int median2 = Math.min(mid1 >= nums1.length ? Integer.MAX_VALUE : nums1[mid1], mid2 >= nums2.length ? Integer.MAX_VALUE : nums2[mid2]);
        return (median1 + median2) * 0.5;
    }
}