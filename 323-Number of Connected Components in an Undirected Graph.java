/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1

Author: Mindy927*/

class Solution {
    class UF{
        int n;
        int[] parent;
        int count = 0;
        
        public UF(int n){
           parent = new int[n];
           for (int i=0; i<n; i++){
               parent[i] = i;
               count++;
           }    
        }
        
        public int find(int x){
            while (x!=parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            parent[rootY] = rootX;
            count--;
        }
    }
    
    public int countComponents(int n, int[][] edges) {
         UF uf = new UF(n);
         for (int[] e:edges){
             uf.union(e[0],e[1]);
         }
        return uf.count;
    }
}