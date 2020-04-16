
/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:
Input:
v1 = [1,2]
v2 = [3,4,5,6] 
Output: [1,3,2,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].

Follow up:
What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
*/


// method 1: Array
// time complexity: O(1), space complexity: O(1)
public class ZigzagIterator {
    int currArrayIndex;
    List<List<Integer>> v;
    int k;
    int[] indexArray;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        currArrayIndex = 0;
        v = new ArrayList<>();
        v.add(v1);
        v.add(v2);
        k = 2;
        indexArray = new int[k];
    }

    public int next() {
        while(indexArray[currArrayIndex] == v.get(currArrayIndex).size()) {
            currArrayIndex = (currArrayIndex + 1) % k;
        }
        int value = v.get(currArrayIndex).get(indexArray[currArrayIndex]);
        indexArray[currArrayIndex]++;
        currArrayIndex = (currArrayIndex + 1) % k;
        return value;
    }

    public boolean hasNext() {
        for(int i = 0; i < k; i++) {
            if(indexArray[i] < v.get(i).size()) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

