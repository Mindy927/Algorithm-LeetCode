/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.

Author: Mind927*/

//keep last seen index of current char for both string
class Solution {
    public boolean isIsomorphic(String s, String t) { 
        int[] idx1 = new int[128];
        int[] idx2 = new int[128];
        Arrays.fill(idx1, -1);
        Arrays.fill(idx2, -1);
        
        for (int i=0; i<s.length(); i++){
           if (idx1[s.charAt(i)] != idx2[t.charAt(i)]) return false;
            idx1[s.charAt(i)] = i;
            idx2[t.charAt(i)] = i;
        }

        return true;
    }
}