/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Author: Mindy927
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word:wordDict){
            set.add(word);
        }
        
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        //dp[i] is true if s.substring(i,j) are in dict && dp[j] is true
        dp[0] = true;
        for (int i=1; i<n+1; i++){
            for (int j=i; j<n+1; j++){
                String word = s.substring(i-1,j);
                if (set.contains(word) && dp[i-1]) dp[j] = true;
            }
        }
        
        return dp[n];
    }
}