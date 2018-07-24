/*
A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

Author: Mindy927 */

class Solution {
    // dp[i-1] , dp[i-2]
    public int numDecodings(String s) {
        int M = 1000000007;
        int n = s.length();
        if (n==0) return 0;
        if (s.charAt(0) == '0') return 0;
        long[] dp = new long[n+1];
        
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9:1;
        for (int i=2; i<n+1; i++){
            char prev = s.charAt(i-2);
            char cur = s.charAt(i-1);

            //1 digit, based on dp[i-1]
            if (cur == '*') dp[i] += dp[i-1] * 9;
            else if (cur > '0') dp[i] += dp[i-1];
            
            //2 digits,base on dp[i-2]
            if (prev == '*'){
                if (cur == '*') dp[i] += dp[i-2] * 15; //15 two digits number
                else if (cur <= '6' ) dp[i] += dp[i-2] * 2; //1X and 2X
                else dp[i] += dp[i-2];
            } else if (prev == '1' || prev == '2'){
                if (cur == '*') {
                    if (prev == '1') dp[i] += dp[i-2] * 9; //* can represent 1-9, not including 0
                    if (prev == '2') dp[i] += dp[i-2] * 6;
                } else if ( (prev -'0')*10 + (cur-'0') <=26 ){
                    dp[i] += dp[i-2];
                }
            }

            dp[i] = dp[i] % M;
        }
        
        return (int)dp[n];
    }
}