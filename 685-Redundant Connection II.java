/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Author: Mindy927*/

/*
Two cases case redundant connection
(1) one node has two parent node -> 1 pass to find two candicate edges, remove cand2
(2) circle
-- if no candidates found in case(1) return current edge form cirle
-- else return cand1
=> no circle (remove cand2 makes tree valid) -> return cand 2

*/
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] cand1 = new int[]{-1, -1};
        int[] cand2 = new int[]{-1, -1};
        int n = edges.length;
        int[] parent = new int[n+1];

        //1 pass to find cand1, cand2 when one node has two parent nodes
        for(int[] e:edges){
            int father = e[0], child = e[1];
            if (parent[child] != 0){
                cand1 = new int[]{parent[child], e[1]};
                cand2 = new int[]{e[0], e[1]};
                e[0] = 0; //remove second edge by connect it to dummy node 0
            } else {
                parent[child] = father;
            }
        }
        
        //initialize parent
        for (int i=0; i<n+1; i++) {
            parent[i] = i;
        }
        
        //2 pass to find circle
        for (int[] e:edges){
            if (e[0] == 0) continue; //edge been removed
            int father = e[0], child = e[1];
           
            if ( find(parent, father) == child){ //circle
                if (cand1[0] == -1) return e; 
                else return cand1; 
            }
            parent[child] = father; //union
        }
        
        return cand2;
        
    }
    
    public int find(int[] parent, int x){
        while ( x != parent[x]){
            x = parent[x];
        }
        return x;
    }
}