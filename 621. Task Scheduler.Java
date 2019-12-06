
/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/


// method 1: HashMap + PriorityQueue
// input size: tasks.length == m, interval == n
// time complexity: O(m * nlogm), space complexity: O(m)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char curr: tasks) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            maxHeap.offer(entry);
        }
        int result = 0;
        while(!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> taskTodo = new ArrayList<>();
            int interval = 0;
            // make sure the interval for the same task > n
            while(interval < n + 1) {
                if(!maxHeap.isEmpty()) {
                    Map.Entry<Character, Integer> currTask = maxHeap.poll();
                    Integer count = currTask.getValue();
                    currTask.setValue(count - 1);
                    // We do not complete currTask, add currTask to taskTodo 
                    if(count > 1) {
                        taskTodo.add(currTask);
                    }
                }
                interval++;
                result++;
                // in case: there is not task left, but result++
                if(taskTodo.isEmpty()) {
                    break;
                }
            }
            // add those uncompleted task back to maxHeap
            for(Map.Entry<Character, Integer> nextTask: taskTodo) {
                maxHeap.offer(nextTask);
            }
        }
        return result;
    }
}


// method 2: int[] map + maxHeap
// input size: tasks.length == m, interval == n
// time complexity: O(result), space complexity: O(26)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] map = new int[26];
        for(char curr: tasks) {
            map[curr - 'A']++;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(26, Collections.reverseOrder());
        for(int count: map) {
            if(count > 0) {
                maxHeap.offer(count);
            }
        }
        int result = 0;
        while(!maxHeap.isEmpty()) {
            int interval = 0;
            List<Integer> taskTodo = new ArrayList<>();
            while(interval <= n) {
                if(!maxHeap.isEmpty()) {
                    int currTask = maxHeap.poll();
                    if(currTask > 1) {
                        taskTodo.add(currTask - 1);
                    }
                }
                interval++;
                result++;
                if(taskTodo.isEmpty()) {
                    break;
                }
            }
            for(int nextTask: taskTodo) {
                maxHeap.offer(nextTask);
            }
        }
        return result;
    }
}


// method 3: int[] map + Math
// input size: tasks.length == m, interval == n
// time complexity: O(m), space complexity: O(26)
class Solution {
     /*
        row number = max number of char
        col number = n + 1
        count the number of idel = all X, 
        result = number of tasks + idel
        A B C D E
        A B C D X
        A B X X X
        A
        */
    public int leastInterval(char[] tasks, int n) {
        if(tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] map = new int[26];
        for(char curr: tasks) {
            map[curr - 'A']++;
        }
        Arrays.sort(map);
        int row = map[25];
        int col = n + 1;
        int idel = (map[25] - 1) * n;
        for(int i = 24; i >= 0; i--) {
            idel -= Math.min(map[i], map[25] - 1);
        }
        return idel > 0 ? tasks.length + idel : tasks.length;
       
    }
}

