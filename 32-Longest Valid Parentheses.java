/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

Author:Mindy927*/

class Solution {
    /* for each ")" encountered it pairs with 
    (1) .... (),  previous "(" OR
    (2) ( .... ) , pair with '(' before dp[i-1] and cancatenate with dp[i-dp[i-1]-1]
    */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n+1]; //dp[i]: longest valid paretheses ends at i-1
        
        int max = 0;
        for (int i=1; i<n+1; i++){
            char c = s.charAt(i-1);
            if ( c == '(') continue;
            if (i>1 && s.charAt(i-2)== '(') dp[i] = dp[i-2] + 2;
            else if (i-1 - dp[i-1] - 1 >= 0 && s.charAt(i-1 - dp[i-1] - 1) == '(') {
                if (i-1 - dp[i-1] - 2 >=0) dp[i] = dp[i-1] + 2 + dp[i-1 - dp[i-1] - 1]; //cancatenate
                else dp[i] = dp[i-1] + 2;
            }
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
}