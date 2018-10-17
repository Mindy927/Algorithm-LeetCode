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

//Method 1: bfs, bfs is better for this case since it will stop once we found shortest path
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (Arrays.equals(start, destination)) return true;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[] DIRS = new int[]{1, 0, -1, 0, 1};
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = true;
        
        while (!q.isEmpty()){
            int size = q.size();
            for (int s=0; s<size; s++){
                int[] pos = q.poll();
                for (int d=0; d<4; d++){
                    int x = pos[0], y = pos[1]; //start from orginal postion in new direction
                    int deltaX = DIRS[d];
                    int deltaY = DIRS[d+1];
                    //find last valid position before hitting wall/obstacle, compare x + deltaX with boundary
                    //move when next posistion is valid
                    while( x+deltaX>=0 && y+deltaY>=0 && x+deltaX<maze.length && y+ deltaY < maze[0].length && maze[x+deltaX][y+deltaY] == 0){
                        x += deltaX; 
                        y += deltaY;
                    }
                    
                    int[] next = new int[]{x,y};
                    if ( x<0 || y<0 || x>=maze.length || y >= maze[0].length ) continue;
                    if (visited[x][y]|| maze[x][y] != 0) continue;
                    if (Arrays.equals(next, destination)) return true;
                    
                    visited[x][y] = true;
                    q.offer(next);
                }    
            }
        }
        
        return false;
    }
}


//Method 2: dfs
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Set<String> visited = new HashSet<>();
        return dfs(maze, start[0], start[1], destination[0], destination[1], visited);
    }
    
    static final int[] DIRS = new int[]{1, 0, -1, 0, 1};
    public boolean dfs(int[][] maze, int i, int j, int destX, int destY, Set<String> visited){
        if (visited.contains( i + "-" + j) || i<0 || j<0 || i>=maze.length || j >= maze[0].length || maze[i][j] != 0) return false;
        
        if (i==destX && j == destY) return true;
        
        visited.add(i + "-" + j);
        for (int d=0; d<4; d++){
            int x = i, y = j;
            int deltaX = DIRS[d];
            int deltaY = DIRS[d+1];
            //find last valid position before hitting wall/obstacle, compare x + deltaX with boundary
            //move when next posistion is valid
            while( x+deltaX>=0 && y+deltaY>=0 && x+deltaX<maze.length && y+ deltaY < maze[0].length && maze[x+deltaX][y+deltaY] == 0){
                x += deltaX; 
                y += deltaY;
            }
            if (dfs(maze, x, y, destX, destY, visited)) return true;
        }
        //visited.remove(i + "-" + j);
        
        return false;
    }
}
