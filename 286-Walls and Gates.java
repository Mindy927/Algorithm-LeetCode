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
        Queue<int[]> q = new LinkedList<>();
        int[] DIRS = new int[]{1,0,-1,0,1};
            
        for (int i=0; i<rooms.length; i++){
            for (int j=0; j<rooms[0].length; j++){
                if (rooms[i][j]==0) q.offer(new int[]{i,j});
            }
        }
    
        int dist = 0;
        while (!q.isEmpty()){
            dist++;
            int size = q.size();
            for (int s=0; s<size; s++){
                int[] pos = q.poll();
                for(int d=0; d<4; d++){
                    int x = pos[0] + DIRS[d];
                    int y = pos[1] + DIRS[d+1];
                    if (x>=0 && x<rooms.length && y>=0 && y<rooms[0].length && rooms[x][y] == Integer.MAX_VALUE){
                        rooms[x][y] = dist; //update distance before adding to queue
                        q.offer(new int[]{x,y});
                    }
                }
            }
        }
    }
}