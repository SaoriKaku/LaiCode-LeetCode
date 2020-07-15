
/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 

The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/


// method 1: dfs
// time complexity: k = number of query, n = number of nodes, O(k * n), space complexity: O(n)
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if(equations == null || equations.size() == 0 || values == null || values.length == 0 || queries == null || queries.size() == 0 || equations.size() != values.length) {
            return new double[0];
        }
        // map: key: node, value: nextNode and its egde value
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i = 0; i < values.length; i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double weight = values[i];
            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).putIfAbsent(to, weight);
            graph.putIfAbsent(to, new HashMap<>());
            graph.get(to).putIfAbsent(from, 1.0 / weight);
        }   
        // traverse queries, dfs find from a to b, a = weight * b
        double[] result = new double[queries.size()];
        Set<String> visited = new HashSet<>();
        for(int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            //System.out.println("i = " + i);
            result[i] = dfs(graph, visited, from, to);
        }
        return result;
    }

    private double dfs(Map<String, Map<String, Double>> graph, Set<String> visited, String from, String to) {
        // System.out.println("from = " + from + ", to = " + to);
        if(!graph.containsKey(from) || !graph.containsKey(to)) {
            return -1.0;
        }
        if(from.equals(to)) {
            return 1.0;
        }
        if(graph.get(from).containsKey(to)) {
            return graph.get(from).get(to);
        }
        visited.add(from);
        for(String next: graph.get(from).keySet()) {
            if(!visited.contains(next)) {
                double temp = dfs(graph, visited, next, to);
                if(temp != -1) {
                    visited.remove(from);
                    return temp * graph.get(from).get(next);
                }
            }   
        }
        visited.remove(from);
        return -1;
    }
}




