/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Author: Mindy927 */

//Use String to record pattern of each island

class Solution {
    static final int[] DIRS = new int[]{0,1,0,-1,0};
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 0, 0, sb);
                    set.add(sb.toString());
                }
            }       
        }
        return set.size();
    }

    public void dfs(int[][] grid, int i, int j, int deltaX, int deltaY, StringBuilder sb){
        sb.append("["+ deltaX + "," + deltaY +"]");
        grid[i+deltaX][j+deltaY] = 0;
        
        for (int d=0; d<4; d++){
            int nextX = deltaX + DIRS[d];
            int nextY = deltaY + DIRS[d+1];
            if (i+nextX <0 || j+nextY<0 || i+nextX>= grid.length || j+nextY >= grid[0].length || grid[i+nextX][j+nextY] == 0)
                continue; //Stop further search when necessary
            dfs(grid, i,j, nextX, nextY,sb);
        }
    
    }
}