/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree. 

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false

Author: Mindy927 */

class Solution {
    class UF {
        int n;
        int[] parent;
        
        public UF(int size) {
            n = size;
            parent = new int[n];
            for (int i=0; i<n; i++){
               parent[i] = i;   
            }
        }
        
        public int find(int x){
            while (x!= parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX==rootY) return;
            parent[rootY] = rootX;
            n--;
        }
        
    }
    
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        
        for (int[] e:edges){
            if ( uf.find(e[0])!=uf.find(e[1]) ) uf.union(e[0],e[1]);
            else return false;
        }
        
        return uf.n==1; //only 1 root
    }
}
