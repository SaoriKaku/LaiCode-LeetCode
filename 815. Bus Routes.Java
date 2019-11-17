
/*
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
*/


/* 
method 1: row = n, col = n
time complexity: O(m * n), space complexity: O(m * n * m)
*/
class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(routes == null || routes.length == 0 || routes[0].length == 0) {
            return -1;
        }
        if(S == T) {
            return 0;
        }
        // Map<key = stop number, value = row number>
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < routes.length; i++) {
            for(int j = 0; j < routes[i].length; j++) {
                List<Integer> rowNumber = map.getOrDefault(routes[i][j], new ArrayList<>());
                rowNumber.add(i);
                map.put(routes[i][j], rowNumber);
            }
        }
        
        int count = 0;
        // visited: store row number, not each stop!
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(S);
        while(!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for(int i = 0; i < size; i++) {
                int curr = queue.poll();
                List<Integer> rowNumbers = map.get(curr);
                for(int rowNumber: rowNumbers) {
                	if(visited.add(rowNumber)) {
                        for(int j = 0; j < routes[rowNumber].length; j++) {
                            int stop = routes[rowNumber][j];
                            if(stop == T) return count;
                            queue.offer(stop);
                        }
                    }
                }
            }// end for
        }// end while
        return -1;
    }
}
