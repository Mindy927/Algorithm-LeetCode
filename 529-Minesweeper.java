/*
Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
 

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]


 Author: Mindy927*/

 class Solution {
    int[][] DIRS = new int[][]{ {1,0},{1,1},{0,1},{-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        
        if (board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        
        dfs(board, x, y);
        return board;
    }

    public void dfs(char[][] board, int i, int j){
        if ( i<0 || i>=board.length || j<0 || j>= board[0].length || board[i][j] != 'E') return;
        
        int cnt = cntMines(board, i, j);
        if (cnt != 0) {
            board[i][j] = (char)(cnt + '0');
            return;
        }
        board[i][j] = 'B';
        //dfs adjacent unrevealed squares
        for (int d=0; d<8; d++){
            int x = i + DIRS[d][0];
            int y = j + DIRS[d][1];
            if (x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y] != 'E') continue;
            dfs(board, x, y); 
        }
        
    }
    
    //return adjacent mines for a empty square
    public int cntMines(char[][] board, int i, int j){
        int cnt = 0;
        for (int d=0; d<8; d++){
            int x = i + DIRS[d][0];
            int y = j + DIRS[d][1];
            if (x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y] == 'M') cnt++;
        }
        
        return cnt;
    }
}