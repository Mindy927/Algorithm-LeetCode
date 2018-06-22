/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Author: Mindy927 */

// Key Idea: connect '0' in the boundry to a dummy node, if a 'O' node is not connected to the dummy node,  change to 'X'.


class Solution {
    int m, n;
    int dummy = m*n;
    class UF{
        int count = 0;
        int[] parent;
        
        public UF(char[][] board){
             parent = new int[m*n+1];
             parent[m*n] = m*n;
             for (int i=0; i<m; i++){
                 for (int j=0; j<n; j++){
                     if (board[i][j] == 'O'){
                         int index = i * n + j;
                         parent[index] = index;
                     }
                 }
             }
        }
        
        public int find(int x){
		    while (x != parent[x]) {
			    parent[x] = parent[parent[x]];    // path compression
			    x = parent[x];
		   }
		    return x;
        }
        
        public void union(int x, int y){
            if (x==y) return;
            int rootX = find(x);
            int rootY = find(y);
            parent[rootY] = rootX;
        }
    }
    
    
    public void solve(char[][] board) {
        if (board.length==0) return;
        m = board.length;
        n = board[0].length;
        UF uf = new UF(board);
         
        //Union all 'O', connect to dummy node when its at boundry
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (board[i][j]== 'O'){
                    if( i == 0 || i == m-1 || j == 0 || j == n-1) {
                        uf.union(i*n+j, dummy);
                    }
                    else {
                        int index = i * n + j;
                        if (i > 0 && board[i-1][j] == 'O') uf.union(index, (i-1)*n+j);
                        if (i+1 < m && board[i+1][j] == 'O') uf.union(index, (i+1)*n+j);
                        if (j>0  && board[i][j-1] == 'O') uf.union(index, i*n+j-1);
                        if (j+1<n  && board[i][j+1] =='O') uf.union(index, i*n+j+1);
                    }
                }
            }
        }
        
        //replace 'O' with 'X' when its not connected to dummy
         for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                int rootDummy = uf.find(dummy);
                if (board[i][j]== 'O' && uf.find(i*n+j)!=rootDummy){
                    board[i][j] = 'X';
                }
            }
         }   
    }
}