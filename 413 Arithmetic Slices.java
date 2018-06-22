/*
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

7,7,7,7 
1,3,5,7

Author: Mindy927 */

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length<=2) return 0;
        int[] dp = new int[A.length];
        int sum = 0;
        //dp[i]: number of arithmetic slices ends at A[i]
        for(int i=2; i<A.length; i++){
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) dp[i] = dp[i-1] + 1;
            else dp[i] = 0;
            sum += dp[i];
        }
        
        return sum;
    }
}