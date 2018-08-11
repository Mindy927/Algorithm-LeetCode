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


//Method 1: heap
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


//Method 2: binary search all possible result (min to max, k = max - min), and verify O(nlgk)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = matrix[0][0], right = matrix[m-1][n-1];
        
        while (left +1 < right){
            int mid = left + (right - left)/2;
            int cnt = count(matrix, mid);
            //if (cnt == k) return mid; we cannot return mid here since they might be number X <mid which has count(X) ==k, need to search further!
            if (cnt < k) left = mid; 
            else right = mid;
        }
        
        return count(matrix,left)>=k? left:right;
    }

    public int count(int[][] matrix, int target){//# of elements <= target
        int cnt = 0;
        for (int i=0; i<matrix.length; i++){
            if (matrix[i][0] > target) break;
            for (int j=0; j<matrix[0].length; j++){
                if (matrix[i][j] > target) break;
                else cnt++;
            }
        }
       return cnt;
    }
}

