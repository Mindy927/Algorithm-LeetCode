


//Method 1: brute force 
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int cnt =0, m = grid.length, n = grid[0].length;
        int[] dp = new int
        for (int i=0; i<m-1; i++){
            for (int j=0; j<n-1; j++){
                if (grid[i][j] == 0) continue;
                for (int a=i+1; a<m; a++){
                    for (int b=j+1; b<n; b++){
                        if (grid[a][j] == 1 && grid[i][b]==1 && grid[a][b]==1) cnt++;
                    }
                }
            }
        }
        
        return cnt;
    }
}

//Method 2: dp O(n^3)/O(n^2)
/*
dp[n][n]
dp[j][k] : #of rows with col[j] & col[k] being 1 before row[i] (form upper corners of rectangle)
— > if grid[i][k] == 1 & gird[i][j] == 1  (it can form lower two conners of rectangle)
*/
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int cnt =0, m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][n];
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n-1; j++){
                if (grid[i][j]!=1) continue;
                for (int k=j+1; k<n; k++){
                    if (grid[i][k]!=1) continue;
                    cnt += dp[j][k];
                    dp[j][k]++;
                }
            }
        }
        return cnt;
    }
}