/*
Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.

Author: Mindy927*/

//reverse whole string, reverse a single word
public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        String[] strs = sb.reverse().toString().split(" ++");//split by multiple space
        
        StringBuilder res = new StringBuilder();
        for (int i=0; i<strs.length; i++){
            String temp = strs[i];
            StringBuilder sbTemp = new StringBuilder(temp);
            res.append(sbTemp.reverse().toString()+ " ");
        }
        return res.toString().trim();
    }
}