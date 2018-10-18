/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Author: Mindy927 */

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        
        //left, right pointer iteraters each col pairs
        for (int l=0; l<n; l++){  
            int[] sum = new int[m]; //sum[i], sum from matrix[i][l] to matrix[i][r]
            for(int r=l; r<n; r++){
                for (int i=0; i<m; i++){
                    sum[i] += matrix[i][r];        
                }
                int temp = maxSubArraySum(sum, k); //max rectangle sum with left bound being l, right bound being r
                res = Math.max(res, temp);
            }   
        }
        
        return res;
    }
    
    //return max sub array sum <= k
    public int maxSubArraySum(int[] nums, int k){
        TreeSet<Integer> set = new TreeSet<Integer>(); //all possible prefix sums
        set.add(0);
        int res=Integer.MIN_VALUE;
        int sum = 0;
        for(int num:nums){
            sum += num;
            // res <=k, hence sum - prev <=k, i.e, prev >= sum - k
            Integer prev=set.ceiling(sum-k); //least element is set >= sum - k
            if(prev!=null) res = Math.max(res, sum-prev);
            set.add(sum);
        }
        return res;
    }
}