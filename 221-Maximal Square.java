/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
Author: Mindy927*/

//dp[i+1] [j+1] : size of max square ends at M[i][j]  (size 2 with area 4)

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1]; //dp[i][j] size of squares ends at M[i][j] (size 3 with area 9)
        
        int max = 0;
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){ 
                if (matrix[i][j] == '1') dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+1][j],dp[i][j+1]))+1;
                max = Math.max(max, dp[i+1][j+1]);
            }
        }
        
        return max * max;
    }
}