
/*
We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

Example 1:
Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: 
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 
Note:
3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
*/


// method 1: Sort + HashMap
// time complexity: O(n^3 + nlogn), space complexity: O(n)
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        if(username == null || timestamp == null || website == null) {
            return new ArrayList<>();
        }
        // key: name, value: sequence of website
        Map<String, List<String[]>> nameSeqMap = new HashMap<>();
        // key: sequence, value: count
        Map<String, Integer> seqCountMap = new HashMap<>();
        for(int i = 0; i < username.length; i++) {
            nameSeqMap.putIfAbsent(username[i], new ArrayList<>());
            String[] pair = new String[]{String.valueOf(timestamp[i]), website[i]};
            nameSeqMap.get(username[i]).add(pair);
        }
        int maxCount = 0;
        String result = "";
        for(String name: nameSeqMap.keySet()) {
            List<String[]> websites = nameSeqMap.get(name);
            Collections.sort(websites, (a, b) -> Integer.valueOf(a[0]) - Integer.valueOf(b[0]));
            Set<String> seqSet = new HashSet<>();
            for(int i = 0; i < websites.size() - 2; i++) {
                for(int j = i + 1; j < websites.size() - 1; j++) {
                    for(int k = j + 1; k < websites.size(); k++) {
                        String sequence = websites.get(i)[1] + " " + websites.get(j)[1] + " " + websites.get(k)[1];
                        //System.out.println("sequence = " + sequence);
                        if(seqSet.add(sequence)) {
                            seqCountMap.put(sequence, seqCountMap.getOrDefault(sequence, 0) + 1);
                        }
                        //System.out.println(seqCountMap.get(sequence) + ", maxCount = " + maxCount);
                        if(seqCountMap.get(sequence) > maxCount) {
                            result = sequence;
                            maxCount = seqCountMap.get(sequence);
                        }
                        else if(seqCountMap.get(sequence) == maxCount) {
                            //System.out.println("sequence = " + sequence);
                            result = sequence.compareTo(result) < 0 ? sequence : result;
                        }
                    }
                }
            }
        }
        List<String> solu = new ArrayList<>();
        String[] temp = result.split(" ");
        for(int i = 0; i < 3; i++) {
            solu.add(temp[i]);
        }
        return solu;
    }
}





