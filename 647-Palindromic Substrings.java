/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.

Author: Mindy927 */

//Method 1: expand at all possible centers
class Solution {
    public int countSubstrings(String s) {
        int cnt = 0;
        for (int i=0; i<s.length(); i++){
            if (i+1 < s.length()) cnt += countPalindrome(s, i, i+1);
            cnt += countPalindrome(s, i, i);
        }
        
        return cnt;
    }
    
    //expand at all possible centers
    public int countPalindrome(String s, int i, int j){
        int cnt = 0;
        while (i>=0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            cnt++;
            i--; j++;
        }
        return cnt;
    }
}

//Method 2: dp
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; //dp[i][j]: is s.substring(i,j+1) a palindromic
        
        /*
        if charAt(i) == charAt(j), dp[i][j] = dp[i+1][j-1] 
        */
        
        int cnt = 0;
        for (int i=n-1; i>=0; i--){
            for (int j=i; j<n; j++){
                if (i==j) dp[i][j] = true; //a single char
                if (s.charAt(i) == s.charAt(j)) {
                    if (i+1 == j) dp[i][j] = true; //i,j adjacent
                    else if (i+1 <= j-1) dp[i][j] = dp[i+1][j-1]; //i+1==j-1, center is a char, e.g, aba
                }
                if (dp[i][j]) cnt++;
            }
            
        }
        
        return cnt;
    }
}