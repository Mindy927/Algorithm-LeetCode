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
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];  //mark diagonal[k] as visited; k = j - i + n-1
        boolean[] diag2 = new boolean[2*n-1]; // mark diagonal[k] as visited; k = i + j
;
        return helper(n, 0, col, diag1, diag2);
    }
    
    public int helper(int n, int row, boolean[] col, boolean[] diag1, boolean[] diag2){
        if (row == n) { //pass row+1 to next round, Add result at next round
            return 1;
        }
    
        int count =0;
        //For each row, for loop choose all possible cols based on visited boolean[]
      for (int j=0; j<n; j++){
            if (!col[j] && !diag1[j-row+n-1] && !diag2[row+j]) {
                col[j] = true;
                diag1[j-row+n-1] = true;
                diag2[row+j] = true;
                count += helper(n, row+1, col, diag1, diag2);
                col[j] = false;
                diag1[j-row+n-1] = false;
                diag2[row+j] = false;
            }
        }
        return count;
    }
}