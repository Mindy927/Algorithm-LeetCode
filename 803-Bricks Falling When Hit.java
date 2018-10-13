/*
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input: 
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation: 
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input: 
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation: 
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 

Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.


Author: Mindy927*/

/*
1. reversely find #bricks drop by counting #bricks connect to row 0 element after each hit
2. use dummy node 0 to connect to all nodes in row 0
*/
class Solution {
    class UF{
        int[] parent;
        int[] size; //size[i] : size of subtree with root i
        public UF(int n){
            parent = new int[n];
            size = new int[n]; 
            for (int i=0; i<n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int x){
            while (x != parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if ( rootX == rootY ) return;
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        UF uf = new UF(m*n+1);
        for(int[] hit:hits){
            if (grid[hit[0]][hit[1]] == 1)
                grid[hit[0]][hit[1]] = 2; // bricks been hit, differantiate from other brick 
        }
        
        //union grid excepting the brick been hit
        for(int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if (grid[i][j] == 1) unionAll(grid, i, j, uf);
            }
        }
        
        //reversely count number of bricks connect to row 0 after each hit
        int[] res = new int[hits.length];
        int prevCnt = uf.size[uf.find(0)]; 
        for(int i=hits.length-1; i>=0; i--){
            int[] hit = hits[i];
            if (grid[hit[0]][hit[1]] == 2) {
                unionAll(grid, hit[0], hit[1], uf);
                grid[hit[0]][hit[1]] = 1;
            }
            
            int temp = uf.size[uf.find(0)]; //still need to find root for 0, cause we randomly choosed a node to be root when union, element 0 may not always be root for all elements in row 0
            // -1 for the the brick got hit
            res[i] = (temp - prevCnt > 0) ? temp - prevCnt - 1 : 0;
            prevCnt = temp;
        }
        
        return res;
    }
    
    public static int[] DIRS = new int[]{1, 0, -1, 0, 1};
    public void unionAll(int[][] grid, int i, int j, UF uf){
        int m = grid.length;
        int n = grid[0].length;
        
        for(int d=0; d<4; d++){
            int x = i + DIRS[d];
            int y = j + DIRS[d+1];
            
            if( x>=0 && x<m && y>=0 && y<n && grid[x][y] == 1 ){
                uf.union( i*n+j+1, x*n+y+1 );     
            }
        }
        // use 0 as pivot to connect all elements in row 0
        if (i==0) uf.union(i*n+j+1, 0);
    }
}