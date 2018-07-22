/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
Author: Mindy927*/


class Solution {
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (int i=0; i<s.length(); i++){
            cnt[s.charAt(i)]++;
        }
        
        int len = 0;
        boolean single = false;
        for (int k:cnt){
            if (k%2 == 0) len+=k;
            else {
                len += k-1;
                single = true;
            }
        }
        
        return single? len+1: len;
    }
}