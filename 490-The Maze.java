/*

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

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

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Author:Mindy927 */

//DFS + helper func roll

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];  //visited starting pointe
        return dfs(maze, visited, start, destination);
    }
    
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    public boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] dest){
         if (visited[start[0]][start[1]]) return false;
         if (Arrays.equals(start, dest)) return true;
         
         visited[start[0]][start[1]] = true;
         for (int[] dir:dirs){
             int[] nextPos = roll(maze, start, dir);
             if (Arrays.equals(nextPos,start)) continue;
             if (dfs(maze,visited, nextPos, dest)) return true;
         }
        
        return false;
    }
    
    public int[] roll(int[][] maze, int[] start, int[] dir){
        int deltaX = dir[0];
        int deltaY = dir[1];
        int x = start[0];
        int y = start[1]; 
        //roll up/down
        if (deltaY == 0){
             while (x+deltaX >=0 && x+deltaX < maze.length && maze[x+deltaX][y] != 1 ){ // Verify whether x+deltaX is valid before updateing x
                  x += deltaX;
             }
        }
        //roll left/right
        if (deltaX == 0){
            while (y+deltaY>=0 && y+deltaY<maze[0].length && maze[x][y+deltaY] != 1) {
                y += deltaY;
            } 
        }           
        return new int[]{x,y};
    }
}