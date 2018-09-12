/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?


Author:Mindy927*/

class Solution {
    class UF{
        int cnt; //number of roots/islands
        int[] parent;
        public UF(int n){
            cnt = 0;
            parent = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
            }
        }
        
        public int find(int x){ //find root
            while ( x != parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return; 
            parent[rootX] = rootY; 
            cnt--;//Key Step: union when roots are not same and update cnt
        }
    }
    
    public static int[] DIRS = new int[]{-1,0,1,0,-1};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        int[][] grid = new int[m][n]; //mark position of islands
        UF uf = new UF(m*n);
        
        for(int[] pos:positions){
            int index = pos[0] * n + pos[1];
            grid[pos[0]][pos[1]] = 1;
            uf.cnt++;
            for (int d=0; d<4; d++){ //union with adjacent islands, reduce cnt will be handled in uf.union
                int x = pos[0] + DIRS[d];
                int y = pos[1] + DIRS[d+1];
                int nextIdx = x * n + y;
                if (x>=0 && y>=0 && x<m && y<n && grid[x][y]==1){ //union when [x,y] is a island
                    uf.union(index, nextIdx);
                }
            }
            result.add(uf.cnt);
        }
        
        return result;
    }
}