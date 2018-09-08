/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

Author: Mindy927 */

//backtracking
//each time, we use two for loops to find first '.' and try to fill it.
class Solution {
    public void solveSudoku(char[][] board) {
        helper(board);
    }
    
    public boolean helper(char[][] board){
        for (int row=0; row<9; row++){
           for (int col=0; col<9; col++){
                if (board[row][col] != '.') continue;
                for (char num = '1'; num<='9'; num++){
                    if (isValid(board, row, col, num)){
                        board[row][col] = num;
                        if (helper(board)) return true;
                        board[row][col] = '.';
                    }
                }
                return false;
            }
        }

        return true;
    }
    
    public boolean isValid(char[][] board, int row, int col, char num){
        for (int i=0; i<9; i++) {
            if (board[row][i]==num) return false;
        }
        for (int i=0; i<9; i++) {
            if (board[i][col]==num) return false;
        }
        
        //check 3*3 box
        int boxRow = row/3;
        int boxCol = col/3;
        for (int i=3*boxRow; i< 3*boxRow+3; i++){
            for (int j=3*boxCol; j<3*boxCol+3; j++){
                if (board[i][j] == num) return false;
            }
        }
        
        return true;
    }
}