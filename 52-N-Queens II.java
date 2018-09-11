/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Author: Mindy927 */

class Solution {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];
        
        return dfs(n, 0, cols, diag1, diag2);
    }
    
    //number of ways when start from row i
    //Similiar to string problem, consider rows as start index, and col as chars we could select in this round
    public int dfs(int n, int i, boolean[] cols, boolean[] diag1, boolean[] diag2){
        if (i == n) return 1; //pass row+1 to next round, Add result at next round
        
        int cnt = 0;
        for (int j=0; j<n; j++){//For each row, for loop choose all possible cols based on visited boolean[]
            if (cols[j] || diag1[i+j] || diag2[i-j + n-1]) continue;
            cols[j] = true;
            diag1[i+j] = true;
            diag2[i-j+n-1] = true;
            cnt += dfs(n, i+1, cols, diag1, diag2);
            cols[j] = false;
            diag1[i+j] = false;
            diag2[i-j+n-1] = false;
        }
        return cnt;
    }
}