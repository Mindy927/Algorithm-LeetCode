/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Author: Mindy927 */

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>(); 
        if (matrix.length == 0 || matrix[0].length == 0) return res; 
        int top =0, bottom = matrix.length-1;
        int left = 0, right = matrix[0].length-1;
           
        while (true){
            //move right for top most row
            for (int j=left; j<=right; j++) res.add(matrix[top][j]);
            top++; 
            if (top > bottom || left > right ) break;
            
            //move down for right most col
            for (int i=top; i<=bottom; i++) res.add(matrix[i][right]);
            right--; //right boundary decrease
            if (top > bottom || left > right ) break;
            
            //move left
            for (int j=right; j>=left; j--) res.add(matrix[bottom][j]);
            bottom--;
            if (top > bottom || left > right ) break;
            
            //move up
            for (int i=bottom; i>=top; i--) res.add(matrix[i][left]);
            left++;
            if (top > bottom || left > right ) break;
        }
        
        return res;
        
    } 
}