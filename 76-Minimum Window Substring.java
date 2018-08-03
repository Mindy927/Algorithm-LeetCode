/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

Authour:Mindy927*/

class Solution {
    public String minWindow(String s, String t) {
        int[] target = new int[128];
        int cnt = 0; //number of chars matched target
        int min = Integer.MAX_VALUE;
        String res = "";
        
        for (int i=0; i<t.length(); i++) target[t.charAt(i)-' ']++;
        
        int i=0, j=0; 
        while ( j<s.length()){
            if (target[s.charAt(j)-' '] > 0){  
                cnt++; //1 more char matched
            } 
            target[s.charAt(j)-' ']--;
            
            if (cnt == t.length()) {
            //find max i such that substring[i-1,j] matches t
               while (i<=j && cnt == t.length()) {
                  target[s.charAt(i)-' ']++;
                  if (target[s.charAt(i)-' '] > 0) cnt--; 
                  i++;
               }
               if (j-i+1 < min) {
                    min = j-i+1;
                    res = s.substring(i-1,j+1);
                }
            }
            
            j++;
        }
        
        return res;
    }
}