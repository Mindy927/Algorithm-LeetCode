/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

Author: Mindy927*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; //dp[i][j]: length of longest palindrome subseq for s.substring[i,j]
             
        /* 
        if charAt(i) == charAt(j), dp[i][j] = dp[i+1][j-1]
        if charAt(i) != charAt(j), dp[i][j] = max(dp[i+1][j], dp[i][j-1])
        */
        
        int max = 0;
        for (int i=n-1; i>=0; i--){
            for (int j=i; j<n; j++){
                if (i==j) {
                    dp[i][j] = 1;              
                }
                else if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = i+1<=j-1? dp[i+1][j-1]+2:2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
                max = Math.max(max, dp[i][j]);
            }     
        }
        
        return max;
    }
}