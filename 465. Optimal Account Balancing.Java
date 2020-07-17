
/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:
A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.

Example 1:
Input:
[[0,1,10], [2,0,5]]
Output:
2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:
Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
Output:
1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/


// method 1: dfs
// time complexity: O(n!), space complexity: O(n)
class Solution {
    /* 
       0    1   20
       -10  10  -5
       +5
    */
    public int minTransfers(int[][] transactions) {
        if(transactions == null || transactions.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] tran: transactions) {
            map.put(tran[0], map.getOrDefault(tran[0], 0) - tran[2]);
            map.put(tran[1], map.getOrDefault(tran[1], 0) + tran[2]);
        }
        List<Integer> list = new ArrayList<>();
        for(int key: map.keySet()) {
            if(map.get(key) != 0) {
                list.add(map.get(key));
            }
        }
        return dfs(list, 0);
    }
    public int dfs(List<Integer> list, int index) {
        if(index == list.size()) {
            return 0;
        }
        int curr = list.get(index);
        if(list.get(index) == 0) {
            return dfs(list, index + 1);
        }
        int result = Integer.MAX_VALUE;
        for(int i = index + 1; i < list.size(); i++) {
            int next = list.get(i);
            if(next * curr < 0) {
                list.set(i, next + curr);
                result = Math.min(result, 1 + dfs(list, index + 1));
                list.set(i, next);
            }
            if(curr + next == 0) {
                break;
            }
        }
        return result;
    }
}

