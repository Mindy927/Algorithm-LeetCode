/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

 Author:Mindy927*/



class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length==0 || rooms[0].length==0) return;
        int m = rooms.length, n = rooms[0].length;

        Queue<int[]> q = new LinkedList<>();  //queue of [i,j]
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (rooms[i][j]==0) q.offer(new int[]{i,j});
            }
        }
        
        int dist = 0;
        int[] DIRS = new int[]{-1,0,1,0,-1};
        while (!q.isEmpty()){
            dist++;
            int size = q.size();
            for (int i=0; i<size; i++){
                int[] cur = q.poll();
                for (int d=0; d<4; d++){
                    int x = cur[0] + DIRS[d];
                    int y = cur[1] + DIRS[d+1];
                    if (inBound(rooms, x, y) && rooms[x][y]==Integer.MAX_VALUE){
                        q.offer(new int[]{x,y});
                        rooms[x][y] = dist;
                    }
                }
            }
        }
    }
    
    
    public boolean inBound(int[][] rooms, int x, int y){
        return (x>=0 && x<rooms.length && y>=0 && y<rooms[0].length);
    }
}