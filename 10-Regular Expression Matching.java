/*

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".


Author: Mindy927 */

class Solution {
    public boolean isMatch(String s, String p) {
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        
        // for c*a*b, dp[0][0], dp[0][2],dp[0][4] is ture since c* can match empty string
        for (int j=2; j<p.length()+1; j++){
            if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j-2];
        }
        
        for (int i=1; i<s.length()+1; i++){
            for (int j=1; j<p.length()+1; j++){
                if  (s.charAt(i-1) == p.charAt(j-1))  dp[i][j] = dp[i-1][j-1];
                
                else if (p.charAt(j-1) == '.' ) dp[i][j] = dp[i-1][j-1];
                
                else if (p.charAt(j-1) == '*' ) {
                    if (s.charAt(i-1) != p.charAt(j-2) && p.charAt(j-2) != '.')  
                        dp[i][j] = dp[i][j-2]; //a* counts  as empty， use j-2 to match i
                    
                    else {
                        dp[i][j] = dp[i][j] || dp[i][j-2]; //a* counts as empty
                        dp[i][j] = dp[i][j] || dp[i][j-1]; //a* counts as a
                        dp[i][j] = dp[i][j] || dp[i-1][j]; //a counts as multiple 
                        /*a counts as multiple 
                               a  *
                             T
                           a   T  T
                           a      T
                           a      T
                         */
                    }
                }
            }
        }
        
        return dp[s.length()][p.length()];
    }
}