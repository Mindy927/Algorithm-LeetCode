/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.


 Author: Mindy927 */

/*  uniform search algorithm
BFS with priorityQueue, min cummulative cost with higher priority
*/
//Dijkstra's Algorithm

class Solution {
    class Point{
        int x;
        int y;
        int dist;
        public Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i=0; i<m; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
       
        int[] DIRS = new int[]{1, 0, -1, 0, 1};
        
        PriorityQueue<Point> pq = new PriorityQueue<Point>( (a,b) -> (a.dist - b.dist));   
        pq.offer(new Point(start[0], start[1], 0));
        
        //poll once node with min dist each time, search all four directions, add new node to pq
        while (!pq.isEmpty()){
            Point cur = pq.poll();
            if (dist[cur.x][cur.y] <= cur.dist) continue; //we have found shorter distance ealier
            dist[cur.x][cur.y] = cur.dist;
             
            for (int d=0; d<4; d++){
                int x = cur.x; //get x value inside the loop, start from pos cur each time
                int y = cur.y;
                int tempDist = cur.dist;
                while ( x + DIRS[d] >=0 && x + DIRS[d] < m && y + DIRS[d+1] >=0 && y + DIRS[d+1] < n && maze[x + DIRS[d]][y + DIRS[d+1]]==0){
                    x += DIRS[d];
                    y += DIRS[d+1];
                    tempDist++;
                }
                pq.offer(new Point(x, y, tempDist));
            }
        }
        
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE? -1:dist[destination[0]][destination[1]];
    }
}




//DFS, archieve, beats 2%
 class Solution {
    int[] DIRS = new int[]{0,1,0,-1,0}; 
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        //dp[i][j] : min steps to reach [i][j] from start point
        int[][] dp = new int[maze.length][maze[0].length]; 
        //instead of set all entries to Integer.MAX_VALUE, set dp[0] = 1 to different from other points
        dp[start[0]][start[1]] = 1; 
        dfs(maze, dp, start, destination);
        return dp[destination[0]][destination[1]] - 1; //offset by 1
    }
    
    //update dp table from start point, and keeping DFS
    public void dfs(int[][] maze, int[][] dp, int[]start, int[] dest){
        if (Arrays.equals(start, dest)) return;
    
        for (int d=0; d<4; d++){
            int i = start[0], j = start[1], len = dp[start[0]][start[1]];
            int x = DIRS[d], y = DIRS[d+1];
            while ( i+x>=0 && j+y>=0 && i+x<maze.length && j+y<maze[0].length && maze[i+x][j+y]!=1){
                i = i+x;
                j = j+y;
                len++;
            }
            //dp[i][j] > 0 means [i][j] has been visited before
            if (dp[i][j]>0 && len >= dp[i][j] ) continue; // Stop when current len > dp[i][j]
            dp[i][j] = len;
            
            dfs (maze, dp, new int[]{i,j}, dest);
        }
    }
}