
/*
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

Example 1:
Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
 
Constraints:
1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
*/


class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();
        if(arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || arr3 == null || arr3.length == 0) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int curr: arr1) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        for(int curr: arr2) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        for(int curr: arr3) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        for(int curr: map.keySet()) {
            if(map.get(curr) == 3) {
                result.add(curr);
            }
        }
        return result;
    }
}
