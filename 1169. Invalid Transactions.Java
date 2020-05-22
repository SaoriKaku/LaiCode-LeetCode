
/*
A transaction is possibly invalid if:
the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.
Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.

Example 1:
Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.

Example 2:
Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]

Example 3:
Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
 
Constraints:
transactions.length <= 1000
Each transactions[i] takes the form "{name},{time},{amount},{city}"
Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
Each {time} consist of digits, and represent an integer between 0 and 1000.
Each {amount} consist of digits, and represent an integer between 0 and 2000.
*/


// method 1: HashMap
// time complexity: O(n^2), space complexity: O(n)
class Solution {
    class Transaction {
        String name;
        int time;
        int amount;
        String city;
        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
    public List<String> invalidTransactions(String[] transactions) {
        if(transactions == null || transactions.length == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        // key: name, value: list of transaction
        Map<String, List<Transaction>> visited = new HashMap<>();
        Set<String> addedToResult = new HashSet<>();
        for(int i = 0; i < transactions.length; i++) {
            boolean invalid = false;
            String[] tran = transactions[i].split(",");
            Transaction newTran = new Transaction(tran[0], Integer.valueOf(tran[1]), Integer.valueOf(tran[2]), tran[3]);
            if(newTran.amount > 1000) {
                result.add(transactions[i]);
                addedToResult.add(newTran.toString());
            }
            if(visited.containsKey(newTran.name)) {
                for(Transaction currTran: visited.get(newTran.name)) {
                    if(!currTran.city.equals(newTran.city) && Math.abs(newTran.time - currTran.time) <= 60) {
                        if(addedToResult.add(currTran.toString())) {
                            result.add(currTran.toString());
                        }
                        if(addedToResult.add(transactions[i])) {
                            result.add(transactions[i]);
                        }
                    }
                }
                visited.get(newTran.name).add(newTran);
            }
            else {
                List<Transaction> list = new ArrayList<>();
                list.add(newTran);
                visited.put(newTran.name, list);
            }
        }
        return result;
    }
}



