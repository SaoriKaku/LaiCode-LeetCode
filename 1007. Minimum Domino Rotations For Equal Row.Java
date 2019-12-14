
/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.
*/


// method1: 3 * HashMap
// time complexity: O(n), space complexity: O(1)
class Solution {
    /*
    for int[] A: HashMap<key = 1-6, value = the count of key in A> mapA
    for int[] B: HashMap as int[] A's map, mapB
    for same value in the same position: HashMap<key = 1-6, value=count of 1-6 in the same position
    int min = Integer.MAX_VALUE;
    for(int i = 1 to 6) {
        int a = mapA.get(i);
        int b = mapB.get(i);
        if(a + b == A.length) {
            min = Math.min(a, b);
        }
        else if(a + b > A.length) {
            min = Math.min(a, b) - mapNoSwap.get(i);
        }
    }
    return min;
    */
    public int minDominoRotations(int[] A, int[] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length) {
            return -1;
        }
        HashMap<Integer, Integer> mapA = new HashMap<>();
        HashMap<Integer, Integer> mapB = new HashMap<>();
        HashMap<Integer, Integer> mapNoSwap = new HashMap<>();
        for(int i = 0; i < A.length; i++) {
            mapA.put(A[i], mapA.getOrDefault(A[i], 0) + 1);
            mapB.put(B[i], mapB.getOrDefault(B[i], 0) + 1);
            if(A[i] == B[i]) {
                 mapNoSwap.put(A[i], mapNoSwap.getOrDefault(A[i], 0) + 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= 6; i++) {
            int a = mapA.get(i) == null ? 0 : mapA.get(i);
            int b = mapB.get(i) == null ? 0 : mapB.get(i);
            int same = mapNoSwap.get(i) == null ? 0 : mapNoSwap.get(i);
            if(a + b - same >= A.length) {
                min = Math.min(a, b) - same;
                //System.out.println("min" + min);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}


// method2: optimazed method1, 3 * int[]
// time complexity: O(n), space complexity: O(1)
class Solution {
    /* idea: 
    for int[] A: HashMap<key = 1-6, value = the count of key in A> mapA
    for int[] B: HashMap as int[] A's map, mapB
    for same value in the same position: HashMap<key = 1-6, value = count of 1-6 in the same position>
    int min = Integer.MAX_VALUE;
    for(int i = 1 to 6) {
        int a = mapA.get(i);
        int b = mapB.get(i);
        int same = mapNoSwap.get(i);
        if(a + b - same >= A.length) {
            min = Math.min(a, b) - same;
        }
    }
    return min;
    */
    public int minDominoRotations(int[] A, int[] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length) {
            return -1;
        }
        int[] mapA = new int[7];
        int[] mapB = new int[7];
        int[] mapNoSwap = new int[7];
        for(int i = 0; i < A.length; i++) {
            int a = A[i];
            int b = B[i];
            mapA[a] += 1;
            mapB[b] += 1;
            if(a == b) {
                 mapNoSwap[a] += 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= 6; i++) {
            int a = mapA[i];
            int b = mapB[i];
            int same = mapNoSwap[i];
            if(a + b - same >= A.length) {
                min = Math.min(a, b) - same;
                //System.out.println("min" + min);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

