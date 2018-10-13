
/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.

Author: Mindy927*/

// O(mn) time, O(n) space



//O(mn) time, O(mn) space, and be reduced to O(n) space, rowsL[i] for temp cnt of enmies on the left in row i
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length; 
        int[][] dpL = new int[m][n]; //number of enemies on the left
        int[][] dpR = new int[m][n];
        int[][] dpU = new int[m][n];
        int[][] dpD = new int[m][n];
        
        //fill from left to right, top down
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == 'W'){
                    dpL[i][j] = 0;  //reset for 'W'
                    dpU[i][j] = 0;
                } else {
                    if (j+1 < n)  dpL[i][j+1] = dpL[i][j] + (grid[i][j] == 'E'? 1:0); //+1 for 'E', do nothing for '0'
                    if (i+1 < m)  dpU[i+1][j] = dpU[i][j] + (grid[i][j] == 'E'? 1:0);
                }
            }
        }
        
        //fill from right to left, bottom up
        for(int i=m-1; i>=0; i--){
            for (int j=n-1; j>=0; j--){
                if (grid[i][j] == 'W'){
                    dpR[i][j] = 0;
                    dpD[i][j] = 0;
                }else {
                    if ( j-1 >=0 ) dpR[i][j-1] = dpR[i][j] + (grid[i][j] == 'E'? 1:0);
                    if ( i-1 >=0 ) dpD[i-1][j] = dpD[i][j] + (grid[i][j] == 'E'? 1:0);                    
                }
            }
        }
        
        //get final res
        int res = 0;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == '0') res = Math.max(res, dpL[i][j] + dpR[i][j] + dpU[i][j] + dpD[i][j]);
            }
        }
        
        return res;
    }
}