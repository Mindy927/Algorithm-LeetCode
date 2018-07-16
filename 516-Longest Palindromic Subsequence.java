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

Author:Mindy927*/


/*
 if s.charAt(i) == s.charAt(j) dp[i][j] = dp[i+1][j-1]+ 2
—fill table row- - ,col ++
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; //dp[i][j] palindrome subSeq of s.substring(i,j+1)
        
        for (int i=n-1; i>=0; i--){
            dp[i][i] = 1;
            for (int j=i+1; j<n; j++){
                if (s.charAt(i) == s.charAt(j)){
                    if (i+1<= j-1) dp[i][j] = dp[i+1][j-1]+2;
                    else dp[i][j] = 2;
                } else{
                    int right = i+1<=j? dp[i+1][j]:0;
                    int left = i<=j-1? dp[i][j-1]:0;
                    dp[i][j] = Math.max(left, right);
                }
            }
        }
        
        return dp[0][n-1];
    }
}