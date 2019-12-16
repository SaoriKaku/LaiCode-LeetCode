
/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/


// method 1: DFS + HashMap + Graph
// time complexity: O(n + nlogn + n!)(build map + sort list + dfs), space complexity: O(2n)(HashMap + DFS)
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        if(tickets == null || tickets.size() == 0) {
            return result;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for(List<String> ticket: tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            List currList = map.get(from);
            if(currList == null) {
                currList = new ArrayList<>();
                currList.add(to);
                map.put(from, currList);
            }
            else {
                currList.add(to);
            }
        }
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> currList = entry.getValue();
            Collections.sort(currList);
        }
        
        List<String> currPath = new ArrayList<>();
        currPath.add("JFK");
        dfs(map, tickets.size(), "JFK", currPath, result);
        return result;
    }
    public void dfs(Map<String, List<String>> map, int n, String from, List<String> currPath, List<String> result) {
        if(result.size() == n + 1) {
            return;
        }
        if(currPath.size() == n + 1) {
            result.addAll(currPath);
            return;
        }
        if(!map.containsKey(from)) {
            return;
        }
        List<String> toList = map.get(from);
        for(int i = 0; i < toList.size(); i++) {
            String to = toList.get(i);
            currPath.add(to);
            toList.remove(i);
            dfs(map, n, to, currPath, result);
            toList.add(i, to);
            currPath.remove(currPath.size() - 1);
        }
    }
}


