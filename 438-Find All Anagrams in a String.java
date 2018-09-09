/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".


Author: Mindy927 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = p.length(); //number of chars that s matches p
        if (s.length() < p.length()) return res;
        
        int[] cnt = new int[26];
        int match = 0;
        for (int i=0; i<p.length(); i++){
            cnt[p.charAt(i) - 'a']++;
        }
        
        for (int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            cnt[cur - 'a']--;
            if (cnt[cur - 'a'] >=0) match++;
            
            if (i >= n) { //remove chars before sliding window
                char prev = s.charAt(i-n);
                cnt[prev - 'a']++;
                if (cnt[prev-'a'] > 0) match--; //need to match 1 more char
            } 
            
            if (match == n) res.add(i-n+1);
        }
        return res;
    }
}