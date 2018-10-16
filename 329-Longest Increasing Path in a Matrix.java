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
Explanation: The longest increasing path is [3, 4, 5, 6]. 

Author: Mindy927*/

// dfs + memorize:if the result for a cell is not calculated, we calculate and cache it; otherwise, we get it from the cache directly
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m][n]; //longest increasing path start from dp[i][j]
        
        for (int i=0; i<m; i++) Arrays.fill(dp[i], -1);
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                res = Math.max(res, dfs(matrix, i, j, dp));
            }
        }
        
        return res;
    }
    
    public static int[] DIRS = new int[]{1, 0, -1, 0, 1};
    
    public int dfs(int[][] matrix, int i, int j, int[][] dp){
        if (dp[i][j] != -1) return dp[i][j];
        
        int temp = 1;
        for (int d=0; d<4; d++){
            int x = i + DIRS[d];
            int y = j + DIRS[d+1];
            if (x>=0 && x<matrix.length && y>=0 && y<matrix[0].length && matrix[x][y] > matrix[i][j]) temp = Math.max(temp, dfs(matrix, x, y, dp)+1);
        }
        
        dp[i][j] = temp;
        return temp;
    }
}