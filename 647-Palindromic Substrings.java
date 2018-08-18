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