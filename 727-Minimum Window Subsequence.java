/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

Author: Mindy927*/


class Solution {
    public String minWindow(String S, String T) {
        int m = S.length();
        int n = T.length();
        //dp[i][j]:start index of shortest subsequence ends at S.charAt(i-1) matches T.substring[0,j-1]
        int[][] dp = new int[m+1][n+1];
        
        for(int i=0; i<=m; i++) dp[i][0] = i+1; //start index is next char to match empty string
        
        for(int i=1; i<=m; i++){
            for (int j=1; j<=n; j++){
                char c1 = S.charAt(i-1);
                char c2 = T.charAt(j-1);
                if (c1 == c2) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = dp[i-1][j]; //current char of S does not match T, use previous index from i-1
            }
        }
        
        //final result cound end at any char of S
        String res = "";
        for (int i=1; i<=m; i++){
            if (dp[i][n] == 0) continue;
            String temp = S.substring(dp[i][n]-1, i);
            if (res.equals("") || temp.length() < res.length()) res = temp;
        }
        
        return res;
    }
}