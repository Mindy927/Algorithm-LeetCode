/*
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

7,7,7,7 
1,3,5,7

Author: Mindy927 */


//version 1: dp
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

//version 2: math
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        int delta = A[1] - A[0];
        int len = 2;
        int cnt = 0;
        
        for (int i=2; i<A.length; i++){
            if (A[i] - A[i-1] == delta) len++;
            else { // new start
                delta = A[i] - A[i-1];
                len = 2;
            }
            
            //for each increasement of exisiting arithmetic sequence, increase len by 1 increase #len-2 sequences(with new end), case [1, 2, 3, 4], increase sequence[2,3,4],[1,2,3,4]
            if (len >= 3) cnt+=len-2; //len-2 arithmetic sequences ends at A[i]
        }
        
        return cnt;
    }
}