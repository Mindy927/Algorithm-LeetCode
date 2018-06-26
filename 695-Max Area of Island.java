/*Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.

Author:Mindy927 */

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == 1){
                    int count[] = new int[1];
                    helper (grid, visited,i,j, count);
                    result = Math.max(result, count[0]);
                }
            }
        }
        
        return result;
    }
    
    //Helper func update max count start from grid[i][j]
    public void helper (int[][] grid, boolean[][] visited,int i,int j,int[] count){
        if (i<0 || i>= grid.length || j<0 || j>= grid[0].length) return;
        if (visited[i][j] || grid[i][j]==0) return;
        
        count[0]++;  //keep counting if grid[i][j] is valid
        visited[i][j] = true;
        helper(grid, visited, i+1, j, count);
        helper(grid, visited, i-1, j, count);
        helper(grid, visited, i, j+1, count);
        helper(grid, visited, i, j-1, count);
        visited[i][j] = false;
    }
}