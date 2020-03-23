
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


// method 1: binary search 
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


// method 2: binary search 
// time complexity: O(min(m, n)), space complexity: O(1)
class Solution {
    /* 
    nums: [1, 2, 3, 4, 5, 6]
    nums1: [1, 2, 5]
    nums2: [3, 4, 6]
         
    left1 right1
    mid1-1, mid1
    left2 right2
    mid2-1, mid2
    find when mid1-1 < mid1 && mid1-1 < mid2
         when mid2-1 < mid1 && mid2-1 < mid2
    mid1-1 > mid2, right1 = mid1 - 1
    3 4
    1 2
    mid2-1 > mid1, left1 = mid1 + 1
    1 2
    3 4
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int len = len1 + len2;
        int left1 = 0, right1 = len1, mid = len / 2;
        while(left1 <= len1) {
            int mid1 = left1 + (right1 - left1)/2;
            int mid2 = mid - mid1;
            int mid1Val = mid1 == len1 ? Integer.MAX_VALUE : nums1[mid1];
            int mid1LeftVal = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int mid2Val = mid2 == len2 ? Integer.MAX_VALUE : nums2[mid2];
            int mid2LeftVal = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            if(mid1LeftVal > mid2Val) {
                right1 = mid1 - 1;
                //System.out.println("right moves to mid");
            }
            else if(mid2LeftVal > mid1Val) {
                left1 = mid1 + 1;
                //System.out.println("left moves to mid");
            }
            else {
                int rightMin = Math.min(mid1Val, mid2Val);
                if(len % 2 == 1) return 1.0 * rightMin;
                int leftMax = Math.max(mid1LeftVal, mid2LeftVal);
                return 1.0 * (rightMin + leftMax) / 2;
            }
        }
        return -1.0;
    }
}


