/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.

Author: Mindy927*/


/*
Binary Indexed Tree
time should be O(log(m) * log(n))
*/

/*
Example: query sum(7), keep flipping last bit set get 7,6,4
 [ 0 1 2 3 ] [ 4 5 ]  [ 6 ]
   BIT[4]     BIT[6]  BIT[7]
*/
class NumMatrix {   
    int m;
    int n;
    int[][] BIT; //BIT is 1-indexed
    int[][] nums;
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        BIT = new int[m+1][n+1];
        nums = new int[m][n];
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) {
        if ( m == 0 || n == 0 ) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for(int i=row+1; i<=m; i+=i&(-i)){ //only number after row+1 will be affected
            for (int j=col+1; j<=n; j+=j&(-j)){
                BIT[i][j] += delta;
            }
        }
    }
    
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if ( m == 0 || n == 0 ) return 0;
        return query(row2, col2) - query(row2, col1-1) - query(row1-1, col2) + query(row1-1, col1-1);
    }
    
    //pass the index of matrix, convert to 1-indexing of BIT
    public int query(int row, int col){
        int sum = 0; 
        for (int i=row+1;i>0; i-= i&(-i)){ //start from row+1 and get previous sum
            for (int j=col+1; j>0; j-=j&(-j)){
                sum += BIT[i][j]; 
            }
        }
        return sum;
    }
}