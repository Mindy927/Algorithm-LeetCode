/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
Author: Mindy927*/


class Solution {
    //make first repeated substring and last repeating substring invalid by remove first/last char
    public boolean repeatedSubstringPattern(String s) {
        String S = s + s;
        return S.substring(1,S.length()-1).indexOf(s)!=-1;
    }
}