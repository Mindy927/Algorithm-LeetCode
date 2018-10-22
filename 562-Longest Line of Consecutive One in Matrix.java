/*
Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.

Author: Mindy927*/

class Solution {
    public int longestLine(int[][] M) {
        int[][] DIRS = new int[][]{{1,0}, {1,-1}, {0,1}, {-1,-1}};
        
        int res = 0;
        for (int i=0; i<M.length; i++){
            for (int j=0; j<M[0].length; j++){
                if (M[i][j] == 1){
                    if (j==0 || M[i][j-1]!=1) //move right, skip duplicate, dfs only when left is not 1
                        res = Math.max(res, dfs(M, i, j, 0, 1));
                    if (i==0 || M[i-1][j]!=1) //move down
                        res = Math.max(res, dfs(M, i, j, 1, 0));
                    if (i==0 || j==0 || M[i-1][j-1] != 1) //move diagonal
                         res = Math.max(res, dfs(M, i, j, 1, 1));
                    if (i==0 || j==M[0].length-1 || M[i-1][j+1] !=1) //move anti-diagonal
                        res = Math.max(res, dfs(M, i, j, 1, -1));
                }
            }
        }
        
        return res;
    }
    
    public int dfs(int[][] M, int x, int y, int deltaX,int deltaY){
        if ( x<0 || y<0 || x>=M.length || y>=M[0].length || M[x][y] == 0) return 0;
        return 1 + dfs(M, x+deltaX, y+deltaY, deltaX, deltaY);
    }
}