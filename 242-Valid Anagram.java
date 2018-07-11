/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false

Author: Mindy927 */


//Method 1: Count Chars in each string
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] cnt1 = countChars(s);
        int[] cnt2 = countChars(t);
        for (int i=0; i<26; i++){
            if (cnt1[i]!=cnt2[i]) return false;
        }
        return true;
    }
    
    private int[] countChars(String s){
        int[] res = new int[26];
        for (int i = 0; i<s.length(); i++){
            res[s.charAt(i)-'a']++;
        }
        return res;
    }
}

//Method 2:Sort 
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        
        return Arrays.equals(chars1, chars2);
    }
}