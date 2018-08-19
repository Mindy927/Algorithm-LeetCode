/*
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:

Author: Mindy927*/

class Solution {
    //move up then down in one iteration, for moving up, when hit the boundary, try move to right, if no possible, move down
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length ==0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        int cnt = 0;
        
        int i = 0, j = 0;
        while (cnt < m*n){
            //move up
            while (i>=0 && j<n){
                res[cnt++] = matrix[i][j];
                i--; j++;
            }
            
            //revert to last valid pos
            i++; j--; 
            //move to next valid position
            if (j+1 < n) j++;   //move to right if possible
            else if (i+1 < m) i++;
            else break;
            
            //move down
            while (j>=0 && i<m){
                res[cnt++] = matrix[i][j];
                i++; j--;
            }
            i--;j++;
            if (i+1 < m) i++;
            else if (j+1 < n) j++;
            else break;
        }
        
        return res;
    }
}