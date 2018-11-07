/*
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

Author: Mindy927*/

class Solution {
    /*
    three state variales:
    - i: current length
    - j: number of As
    - k: number of trailing L
    */
    public int checkRecord(int n) {
        final int M = 1000000007;
        //dp[i][j][k]: number of ways to get length i, with at most j A's and at most k trailing Ls
        int[][][] dp = new int[n + 1][2][3]; 
        
        dp[0] = new int[][]{{1,1,1}, {1,1,1}}; // 2*3 size for intialize 
        for (int i=1; i<=n; i++){
            for (int j=0; j<2; j++){
                for (int k=0; k<3; k++){
                    dp[i][j][k] += dp[i-1][j][2] % M; // ...P
                    if (j>0) dp[i][j][k] = (dp[i][j][k] + dp[i-1][j-1][2]) % M; //...A
                    if (k>0) dp[i][j][k] = (dp[i][j][k] + dp[i-1][j][k-1]) % M; //...L
                }
            }
            
        }
        
        return dp[n][1][2];
    }
}