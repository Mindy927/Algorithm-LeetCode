/*
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.

Author: Mindy927 */

class Solution {
    public int rotatedDigits(int N) {
        //dp[i] = 0, invalid,
        //dp[i] = 1, i is valid, rotated back to same nubmer 
        //dp[i] = 2, i is valid, different
        int[] dp = new int[N+1]; 
        int cnt = 0;
        
        for (int i=0; i<N+1; i++){ //even if we choose from 1, dp[0] need to set to 1, accessed when temp2[0]
            if ( i < 10 ){
                if ( i==1 || i==8 || i==0 ) dp[i] = 1;
                if ( i==2 || i==5 || i==6 || i==9) {
                    dp[i] = 2;
                    cnt++;
                }
            }
            else {
                int temp1 = dp[i/10]; //left part
                int temp2 = dp[i%10]; //right part, last digit
                if ( temp1 == 1 && temp2 == 1 ) dp[i] = 1;
                else if ( temp1 >=1 && temp2 >=1 ) { //either left part or right part could rotate to different digit
                    dp[i] = 2;
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}