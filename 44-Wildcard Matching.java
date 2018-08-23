/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

Author:Mindy927*/

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1]; //dp[i][j] whether p.substring(0,j) map to s.substring(0,i)
        
        dp[0][0] = true; //both s and p are empty strings
        //* could map to empty string
        for (int j=1; j<p.length()+1; j++){
            if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j-1];
        }
        
        for (int i=1; i<s.length()+1; i++){
            char cur = s.charAt(i-1);
            for (int j=1; j<p.length()+1; j++){
                char c = p.charAt(j-1);
                if ( c == cur || c == '?') dp[i][j] = dp[i][j] || dp[i-1][j-1]; 
                else if ( c == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    //dp[i-1][j]: * map to any sequence , true will be passed down in the col of '*'
                    //dp[i][j-1]: * map to empty 
                }
            }
        } 
        return dp[m][n];
    }
}