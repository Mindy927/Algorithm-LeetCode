/*

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Author: Mindy927*/

 //Memorize:if the result for a cell is not calculated, we calculate and cache it; otherwise, we get it from the cache directly

class Solution {
    static final int[] DIRS = new int[]{0,1,0,-1,0};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length==0) return 0;
        int[][] memorize = new int[matrix.length][matrix[0].length]; 
       
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                 dfs(matrix, memorize, i, j);
             }
        }
        
        int max = 0;
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                max = Math.max(memorize[i][j], max);
            }
        }
        
        return max+1; 
    }
    

    public void dfs(int[][] matrix,int[][] memorize, int i, int j){
       
        if (memorize[i][j] !=0) return; // Has been calculated
        
        for (int d=0; d<4; d++){
            int x = i + DIRS[d];
            int y = j + DIRS[d+1];
            
            if (x<0 || y<0 || x>=matrix.length || y>= matrix[0].length || matrix[x][y] >= matrix[i][j]) continue;
            dfs(matrix, memorize, x, y); //update memorize based on neighbor[x,y]
            memorize[i][j] = Math.max(memorize[i][j], 1 + memorize[x][y]);
        }
    }
}