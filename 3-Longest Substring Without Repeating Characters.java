/*
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

Author: Mindy927*/
class Solution {
    //move left pointer to last index of current char when necessay
    //e.g, a b c d b (for second b, move left pointer to first b)
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>(); //char:previous index of this char
        
        int res = 0;
        int prev = -1; //last index curChar OR previouly left pointer
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (map.containsKey(c)) prev = Math.max(prev, map.get(c));
            res = Math.max(i-prev, res);
            map.put(c, i);
        }
        
        return res;
    }
}