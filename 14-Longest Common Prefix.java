/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.
Author: Mindy927 */

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String res = strs[0];
        for (String str:strs){
            int i=0;
            for (;i<Math.min(res.length(), str.length()); i++){
                if (res.charAt(i)!=str.charAt(i)) break;
            }
            res = res.substring(0,i); 
            if (res.equals("")) return res;
        }
        
        return res;
    }
}