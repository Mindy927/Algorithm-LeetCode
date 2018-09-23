/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

Author:Mindy927*/


class Solution {
    String res = "";
    public String longestPalindrome(String s) {
        for (int i=0; i<s.length(); i++){
            extendFrom(s, i, i);//aba
            if (i+1<s.length() && s.charAt(i) == s.charAt(i+1))//abba
                extendFrom(s, i, i+1);
        }
        return res;
    }
    
    public void extendFrom(String s, int start, int end){
        while(start-1 >= 0 && end+1<s.length() && s.charAt(start-1) == s.charAt(end+1)){ //make sure start-1 and end+1 is valid before update boundary
            start--;end++;
        }
        
        String temp = s.substring(start, end+1);
        res = temp.length() > res.length()? temp:res;
    }
}