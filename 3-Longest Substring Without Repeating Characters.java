/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

Author: Mindy927*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>(); //char:latest index
        int prev = -1, max =0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            prev = map.containsKey(c)? Math.max(map.get(c),prev):prev; //if map.containsKey(c), prev is max(prev, map.get(c))
            max = Math.max(max, i-prev);
            map.put(c,i);
        }
        
        return max;
    }
}