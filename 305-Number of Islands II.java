/*A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3] 

Author: Mindy927 */


class Solution {
    class UF{
        int count = 0;
        int m,n;
        int[] parent;
        public UF(int row, int col){
            m = row;
            n = col;
            parent = new int[m*n];  
            for (int i=0; i<m*n; i++){
                parent[i] = -1;
            }
        }
        
        public int find (int x){
            if (parent[x] == x) return x;
            while (parent[x] !=x ){
                parent[x] = parent[parent[x]];  //path compression
                x = parent[x];
            }
            return x;
        }
        
        public void union (int x, int y){ 
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            parent[rootX] = rootY;
            count--;
        }
    }
    
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        UF uf = new UF(m,n);
        int[][] delta = {{0,1},{0,-1},{1,0},{-1,0}};
        for (int[] pos:positions){
            int index1 = pos[0] * n + pos[1];
            if (uf.parent[index1] == -1 ){
                uf.parent[index1] = index1;
                uf.count++;
            }
            for (int[] d:delta){
                int x = pos[0] + d[0];
                int y = pos[1] + d[1];
                int index2 = x*n+y;
                if (x>=0 && y>=0 && x<m && y<n && uf.parent[index2]!= -1 ){
                    uf.union(index1, index2);
                }
            }
            result.add(uf.count);
        }
        return result;
    }
}