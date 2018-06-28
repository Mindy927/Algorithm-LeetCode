
/*Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

i.e, Find points can reach both oceans.
Author: Mindy927 */


//Key Idea: Think From The Oceans —>  Start from ocean, mark the point which is higher than current height as True
//boolean[][] visitedP is true when it can be reached from pacific ocean

class Solution {
    int m,n;
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix.length==0) return result;
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] visitedP = new boolean[m][n];
        boolean[][] visitedA = new boolean[m][n];
        
        for (int i=0; i<m; i++){ 
            dfs(matrix, visitedP, i, 0, Integer.MIN_VALUE); //build visited P start from left boundary
            dfs(matrix, visitedA, i, n-1, Integer.MIN_VALUE); //right
        }
        
        for(int j=0; j<n; j++){
            dfs(matrix, visitedP, 0, j, Integer.MIN_VALUE); //upper
            dfs(matrix, visitedA, m-1, j, Integer.MIN_VALUE); //lower
        }
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (visitedA[i][j] && visitedP[i][j]) result.add(new int[]{i,j});
            }
        }
        return result;
    }
      
    public void dfs(int[][] matrix,boolean[][] visited, int i,int j,int height){  
        if ( i<0 || j<0 || i>=m || j>=n || visited[i][j] || matrix[i][j] < height) return;         
        visited[i][j] = true;
        for(int[]d:dir){
            dfs(matrix, visited, i+d[0], j+d[1], matrix[i][j]);
        }
        //visitedP[i][j] = false;
    }   
}
