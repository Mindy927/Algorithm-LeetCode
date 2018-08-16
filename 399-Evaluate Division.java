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

Author: Mindy927*/
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Double> valueMap = new HashMap<>(); //map for a/b:val
        Map<String, Set<String>> dividend = new HashMap<>(); // map for a: all possible dividend for a 
        for (int i=0; i<equations.length; i++){
            String a = equations[i][0];
            String b = equations[i][1];
            double val = values[i];
            
            valueMap.put( a + "/" + b, val);
            if (!dividend.containsKey(a)) dividend.put(a, new HashSet<>());
            dividend.get(a).add(b);
            
            if (val!=0) {
                valueMap.put( b + "/" + a, (double) 1/val);
                if (!dividend.containsKey(b)) dividend.put(b, new HashSet<>());
                dividend.get(b).add(a);
            }
        }
        
        double[] result = new double[queries.length];
        for (int i=0; i<queries.length; i++){
            String a = queries[i][0];
            String b = queries[i][1];
            Set<String> visited = new HashSet<>();
            result[i] = dfs(a, b, visited, valueMap, dividend);
        }
        return result;
    }
    
    public double dfs(String a, String b, Set<String> visited, Map<String, Double> valueMap, Map<String, Set<String>> dividend){
        if (!dividend.containsKey(a)) return -1;
        if (a.equals(b)) return 1;
        if (visited.contains(a) || visited.contains(b)) return -1;
        
        visited.add(a);
        for (String next:dividend.get(a)){
            double temp = dfs(next, b, visited, valueMap, dividend);
            if (temp != -1) return valueMap.get(a + "/" + next) * temp; // a/next , next/b
        }
        visited.remove(a);
        
        return -1;
    }
}