/*
Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.

Author: Mindy927*/

//two pass dp from left/right
class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] dp = new int[S.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        //1 pass to update dp[i] with shortest distance to left C
        for (int i=0; i<S.length(); i++){
            if (S.charAt(i) == C) dp[i] = 0;
            else {
                if (i>0 && dp[i-1] != Integer.MAX_VALUE) dp[i] = dp[i-1] + 1; 
            }
        }
        
        //2 pass to update dp[i] with shortest distance to right C
        for (int i=S.length()-2; i>=0; i--){
            if (i<=S.length()-1 && dp[i+1] != Integer.MAX_VALUE) 
                dp[i] = Math.min(dp[i+1]+1, dp[i]);
        }
        
        return dp;
    }
}