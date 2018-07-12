/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.

Author: Mindy927*/


//Merge sort
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(m*n, new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
               return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
           } 
        });
        
        //Add first element in each row to pq
        for (int i=0; i<m; i++){ 
            pq.offer(new int[]{i,0}); 
        }
        
        while (k-- >1){
            int[] cur = pq.poll();
            int i = cur[0];
            int j = cur[1];
            if (j+1 < n) pq.offer(new int[]{i, j+1});
        }
        
        int[] kth = pq.poll();
        return matrix[kth[0]][kth[1]];
    }
}