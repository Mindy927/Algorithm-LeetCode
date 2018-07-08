/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6

Author:Mindy927 */


class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length==0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] height = new int[n+1];
        
        for (int i=0; i<m; i++){
            Stack<Integer> stack = new Stack<>(); //stack of indices with lower height
            //update height array
            for (int j=0; j<n; j++){
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            
            //cal max rectangle in current row
            int j=0;
            while (j<n+1){
                //when height INCREASING, add to stack 
                if ( stack.isEmpty() || height[stack.peek()] <= height[j]){ 
                    stack.push(j++);
                } else{
                    int cur = stack.pop();
                    // curMax for last element in the stack, excluding jth element => Height array of size n+1
                    int curMax =height[cur] * (stack.isEmpty()? j:(j - stack.peek() - 1)) ;
                    //stack is empty means all previous index with height>= curHeigh
                    max = Math.max(curMax, max);
                }
            }
        }
        
        return max;
    }
}