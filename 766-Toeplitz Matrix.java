/*
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 

Example 1:

Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.


Follow up:
1.What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
  -- Store one row and compare row by row
2.What if the matrix is so large that you can only load up a partial row into the memory at once?

Author: Mindy927*/

//O(1) space, compare each val with its right-down value
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if (i+1 < matrix.length && j+1 < matrix[0].length && matrix[i][j] != matrix[i+1][j+1]) return false;
            }
        }
        
        return true;
    }
}

// O(m+n) space, good for follow-up 1
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] diag = new int[m+n-1]; //diagnal[i]: value for ith diagnal(left-bottom to upper-right)
        Arrays.fill(diag, -1);
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (diag[ j - i + m-1] != -1 && matrix[i][j] != diag[ j - i + m-1]) return false;
                diag[ j - i + m-1] = matrix[i][j];
            }
        }
        
        return true;
    }
}