
/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note: The number of people is less than 1,100.

Example
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/


/*
input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
sort:  [[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]
output:[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/
// method 1: sort
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people == null || people.length == 0) {
            return people;
        }
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> temp = new LinkedList<>();
        for(int[] curr: people) {
            temp.add(curr[1], curr);
        }
        int[][] result = new int[people.length][2];
        for(int i = 0; i < temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}






