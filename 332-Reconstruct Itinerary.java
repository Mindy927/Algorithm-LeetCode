/*Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

Author: Mindy927 */


class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>(); //airport:all the possible destinations 
        List<String> result = new ArrayList<>();
        
        for (String[] t:tickets){
            map.putIfAbsent(t[0], new PriorityQueue<>());
            map.get(t[0]).add(t[1]);
        }
        dfs("JFK",map,result);
        return result;
    }
    
    //greedy, add arrival airport with smallest lexical order
    public void dfs(String depature, Map<String, PriorityQueue<String>> map,List<String> result){
         while (map.containsKey(depature) && !map.get(depature).isEmpty()){ //while loop here since may go back to same city
             dfs(map.get(depature).poll(), map, result);
         }
         //add airport only when it cannot reach anywhere else -> current destination
         result.add(0,depature); 
    }
}