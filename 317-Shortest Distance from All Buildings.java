/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.

Author: Mindy927 */

/*
dist[i][j] record total distance to all houses
reach[i][j] record number of houses that can reach pos i,j
*/
class Solution {
    //bfs, distance from current house is 1,2,3 etx
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] reach = new int[m][n]; //number of houses that reach current pos
        int[][] dist = new int[m][n];
        int cnt = 0; //number of houses
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == 1){
                    cnt++;
                    bfs(grid, dist, reach, i, j);
                }
            }
        }
        
       int min = Integer.MAX_VALUE;
       for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == 0 && reach[i][j] == cnt){
                    min = Math.min(dist[i][j], min);
                }
            }
        }
        
        return min==Integer.MAX_VALUE? -1:min;
    }
    
    static final int[] DIRS = new int[]{-1,0,1,0,-1};
    public void bfs(int[][] grid, int[][] dist, int[][] reach, int i, int j){
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        int level = 0;
        
        while (!q.isEmpty()){
            level++;
            int size = q.size();
            for (int s=0; s<size; s++){
                int[] pos = q.poll();
                
                for (int d=0; d<4; d++){
                    int x = pos[0] + DIRS[d];
                    int y = pos[1] + DIRS[d+1];
                    
                    if (x<0 || y<0 || x>=grid.length || y>=grid[0].length || grid[x][y]!=0 || visited[x][y]) continue;
                    dist[x][y] += level;
                    reach[x][y]++;
                    
                    visited[x][y] = true;
                    q.offer(new int[]{x,y});
                }
            }
        }
        
    }
}