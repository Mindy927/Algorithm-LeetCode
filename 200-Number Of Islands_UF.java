/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and
is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all
surrounded by water.
Example 1:
11110
11010
11000
00000
Answer: 1
Example 2:
11000
11000
00100
00011
Answer: 3
"""
__author__ = 'Mindy927'*/



class Solution {
    class UF{
    int[] parent;
    int m,n;
    int count=0;
    
    public UF(char[][] grid){
        m = grid.length;
        n = grid[0].length;
        parent = new int[m*n];
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == '1'){
                    int index = i*n+j;
                    parent[index] = index;
                    count++;
                }
            }
        }
    }
    
    public void union (int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX==rootY) return;
        parent[rootY] = rootX;
        count--;
    }
    
    public int find (int x){
        if (parent[x] == x) return x;
        else return find(parent[x]);
    }
}
    
    
    
    public int numIslands(char[][] grid) {
        if (grid.length==0) return 0;
        int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
        UF uf = new UF(grid);
        
        int m = grid.length;
        int n = grid[0].length;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == '1'){
                    for (int[] d:distance){
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x>=0 && x<m && y>=0 && y<n && grid[x][y] == '1'){
                            int index1 = n * i + j;
                            int index2 = n * x + y;
                            uf.union(index1, index2);
                        }
                   }
                }
            }
                
        }
        
        
        return uf.count;
    }
}
