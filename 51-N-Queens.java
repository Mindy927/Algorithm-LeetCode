/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

Queue can attack each other in the same row, same col, same diagnal

Author: Mindy927 */

class Solution {
    public List<List<String>> solveNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];  //mark diagonal[k] as visited; k = j - i + n-1
        boolean[] diag2 = new boolean[2*n-1]; // mark diagonal[k] as visited; k = i + j
            
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        helper(n, 0, col, diag1, diag2, temp, result);
        return result;
    }
    
    public void helper(int n, int row, boolean[] col, boolean[] diag1, boolean[] diag2, List<String> temp, List<List<String>> result){
        if (row == n) { //pass row+1 to next round, Add result at next round
            result.add(new ArrayList<>(temp));
            return;
        }
    
        //For each row, for loop choose all possible cols for Q based on visited boolean[]
      for (int j=0; j<n; j++){
            if (!col[j] && !diag1[j-row+n-1] && !diag2[row+j]) {
                col[j] = true;
                diag1[j-row+n-1] = true;
                diag2[row+j] = true;
                StringBuilder sb = new StringBuilder();
                for (int k=0; k<n; k++){
                    if (k==j) sb.append("Q");
                    else sb.append(".");
                }
                temp.add(sb.toString());
                helper(n, row+1, col, diag1,diag2, temp, result);
                temp.remove(temp.size()-1);
                col[j] = false;
                diag1[j-row+n-1] = false;
                diag2[row+j] = false;
            }
        }
    }
}



