/*We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

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

Author: Mindy927*/

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        Map<Integer, Set<Integer>> map = new HashMap<>(); //stop : buses go through current stop
        Set<Integer> visited = new HashSet<>(); // bus stops that has been visited
        
        for (int bus=0; bus<routes.length; bus++){
            for(int stop:routes[bus]){
                if (!map.containsKey(stop)) map.put(stop, new HashSet<>());
                map.get(stop).add(bus);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);
        visited.add(S);
        
        int step = 0;
        while (!q.isEmpty()){
            step++;
            int size = q.size();
            for (int s = 0; s<size; s++){
                int curStop = q.poll(); //stops can reach in current step
                //get all buses who stop at current station, add stops they go through(can reach without transfer)
                for(int bus:map.get(curStop)){ 
                    for(int nextStop:routes[bus]){
                        if (nextStop == T) return step;
                        if (!visited.contains(nextStop)){
                            visited.add(nextStop);
                            q.offer(nextStop);
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}