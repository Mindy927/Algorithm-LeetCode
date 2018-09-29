/*
The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

*/

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        boolean[] visited = new boolean[graph.length];
        dfs(graph, visited, 0, temp, res);
        return res;
    }
    
    public void dfs(int[][] graph, boolean[] visited, int i, List<Integer> temp, List<List<Integer>>res){
        temp.add(i);
        if ( i == graph.length-1){
            res.add(new ArrayList<>(temp));
            temp.remove(temp.size()-1); //remove last element before return
            return;
        } 
        
        visited[i] = true;
        for(int nei:graph[i]){
            if (visited[nei]) continue;
            dfs(graph, visited, nei, temp, res);
        }
        temp.remove(temp.size()-1);
        visited[i] = false;
    }
}