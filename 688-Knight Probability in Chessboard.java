/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

Example:
Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.

Author: Mindy927*/

//Recursion, time limit exceed
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = new int[][]{{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
        if (r<0 || c<0 || r>=N || c>=N) return 0;
        if (K == 0) return 1;
        
        double result = 0;
        for (int i=0; i<8; i++){
            int x = r + dirs[i][0];
            int y = c + dirs[i][1];
            result += knightProbability(N, K-1, x, y);
        }
        
        return result/8;
    } 
}


/*DP, accepted
dp[k][i][j]: number of ways knight stay on the board after k moves --> reduce to 2-D, create new 2-D dp table at start of each iteration
dp[k][i][j] += dp[k-1][x][y];  (x,y) is pos can be reached from (i,j)
*/
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = new int[][]{{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        double[][] prev = new double[N][N]; //dp[k][i][j]: number of ways knight stay on the board after k moves --> reduce to 2-D
        
        for (int i=0; i<N; i++){
            Arrays.fill(prev[i], 1);
        }
        
        for (int k=0; k<K; k++){
            double[][] cur = new double[N][N];
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    for (int[] dir:dirs){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x>=0 && y>=0 && x<N && y<N) cur[i][j] += prev[x][y];
                    }                    
                }
            }
            prev = cur;
        }
        
        return prev[r][c]/Math.pow(8,K);
    } 
}
