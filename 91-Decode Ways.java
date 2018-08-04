/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Author: Mindy927 */


class Solution {
    //three case: dp[i] = dp[i-1], dp[i-2], dp[i-1]+dp[i-2]
    //corner case: 1) start with 0  2) cur == 0, prev != 1 or 2
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n];
        if (s.length() == 0 || s.charAt(0)=='0') return 0;
        dp[0] = 1;
        
        for(int i=1; i<n; i++){
            int prev = s.charAt(i-1) - '0';
            int cur = s.charAt(i) - '0';
            if (cur == 0) {
                if (prev == 0 || prev >2) return 0;
                dp[i] = i>=2? dp[i-2]:(prev==1||prev==2)? 1:0;
            }
            else if (prev == 1 && cur != 0 ) dp[i] = i>1? dp[i-1] + dp[i-2]:dp[i-1]+1; //11-19
            else if (prev == 2 && cur > 0 && cur <=6) dp[i] = i>1? dp[i-1] + dp[i-2]:dp[i-1]+1; //21-26
            else dp[i] = dp[i-1];
        }
        
        return dp[n-1];
    }
}