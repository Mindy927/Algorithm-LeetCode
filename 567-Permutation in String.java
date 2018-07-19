/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Author: Mindy927 */

class Solution {
    //sliding window, cnt array for s1,s2, return true if cnt arrays are the same
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (m<n) return false;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        
        //initialize 
        for (int i=0; i<n; i++){
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)) return true;
        
        //sliding window
        for (int i=0; i<m-n; i++){ //delete ith char and add i+nth 
            cnt2[s2.charAt(i) - 'a']--;
            cnt2[s2.charAt(i+n) - 'a']++;
            if (Arrays.equals(cnt1, cnt2)) return true;
        }
        return false;
    }
}