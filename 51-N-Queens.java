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
    //fill row by row, update vistedCol,visitedDiag matrix
    public List<List<String>> solveNQueens(int n) {
        //boolean[] visitedRow = new boolean[n];
        boolean[] visitedCol = new boolean[n];
        boolean[] visitedDiag1 = new boolean[2*n-1]; //mark diagonal[k] as visited; k = i + j
        boolean[] visitedDiag2 = new boolean[2*n-1]; //mark diagonal[k] as visited; k = j - i + n-1
        List<String> temp = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        
        dfs(visitedCol, visitedDiag1, visitedDiag2, n, 0, temp, result);
        return result;
    }
    
    public void dfs(boolean[] visitedCol, boolean[] visitedDiag1, boolean[] visitedDiag2, int n, int i, List<String> temp, List<List<String>> result){
        if (i == n){
            if (temp.size()==n) result.add(new ArrayList<>(temp));
            return;
        }
        
        //For each row, for loop choose all possible cols for Q based on visited boolean[]
        for (int j=0; j<n; j++){
            if (visitedCol[j] || visitedDiag1[i+j] || visitedDiag2[i-j+n-1]) continue;
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[j] = 'Q';
            
            temp.add(String.valueOf(chars));
            visitedCol[j] = true;
            visitedDiag1[i+j] = true;
            visitedDiag2[i-j+n-1] = true;
            dfs(visitedCol, visitedDiag1, visitedDiag2, n, i+1, temp, result);//search next row
            visitedCol[j] = false;
            visitedDiag1[i+j] = false;
            visitedDiag2[i-j+n-1] = false;
            temp.remove(temp.size()-1);
        }
    }
}


