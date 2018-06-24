/*There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

Author: Mindy927 */

class Solution {
     class UF{
        int n;
        int[] parent;
        int count;
        
        public UF(int n){
            parent = new int[n];
            count = n;
            for (int i=0; i<n; i++){
                parent[i] = i;
            }
        }
        
        public int find(int x){
            while (x!=parent[x]){
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            parent[rootY] = rootX;
            count--;
         }
    }
        
    public int findCircleNum(int[][] M) {
        UF uf = new UF(M.length);
        int n = M.length;
        
        for (int i=0; i< n; i++){
            for (int j=i; j<n; j++){
                if (M[i][j]==1){
                    uf.union(i,j);
                }
            }
        }
        return uf.count;
    }
}