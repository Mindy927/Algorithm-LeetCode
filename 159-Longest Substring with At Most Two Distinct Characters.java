/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

Author: Mind927*/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] cnt = new int[128]; //count for chars in sliding window
        Set<Character> set = new HashSet<>(); //chars in sliding window
        int max = 0;
        
        int i=0;
        for (int j=0; j<s.length(); j++){
            char c = s.charAt(j);
            cnt[c]++;
            if (cnt [c] > 0) set.add(c);
            if (set.size() <= 2) max = Math.max(max, j-i+1);
            while ( i< j && set.size() > 2){ //move left pointer
                char prev = s.charAt(i);
                cnt[prev]--;
                if (cnt[prev] == 0) set.remove(prev);
                i++;
            }
            max = Math.max(max, j-i+1);
        }
        
        return max;
    }
}