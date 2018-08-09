/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

Author: Mindy927 */

class Solution {
    public boolean exist(char[][] board, String word) {
        if (word.length()==0) return false;
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (board[i][j] == word.charAt(0)){
                    if (dfs(board,visited, word, 0, i, j)) return true;
                }
            }
        }
        
        return false;
    }
    
    static final int[] DIRS = new int[]{-1,0,1,0,-1};
    public boolean dfs(char[][] board, boolean[][] visited, String word, int index, int i, int j){
        if (index == word.length()) return true;
        if (i<0 || i>=board.length || j<0 || j>=board[0].length) return false;
        if (board[i][j]!=word.charAt(index) || visited[i][j]) return false;
        
        visited[i][j] = true;
        for (int d=0; d<4; d++){
            int x = i + DIRS[d];
            int y = j + DIRS[d+1];
            if (dfs(board, visited, word, index+1, x, y)) return true;
        }
        visited[i][j] = false;
        
        return false;
    }
}