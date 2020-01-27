
/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
*/


// method 1: time complexity: O(logn + k), space compelxity: O(1)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == x) {
                left = mid;
                right = mid + 1;
            }
            else if(arr[mid] < x) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        //System.out.println("left = " + left + ", right = " + right);
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while(i < k) {
            if(right >= arr.length || left >= 0 && x - arr[left] <= Math.abs(arr[right] - x)) {
                left--;
            }
            else {
                right++;
            }
            i++;
        }
        //System.out.println("left = " + left + ", right = " + right);
        for(i = 1; i <= k; i++) {
            result.add(arr[left + i]);
        }
        return result;
    }
}



