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

//Method 1:dp, time O(n^2), space O(n)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        
        for(String word:wordDict) set.add(word);
        
        boolean[] dp = new boolean[s.length()+1]; //dp[i] is true if s.substring(0,i) has valid word break
        dp[0] = true;
        
        for(int i=1; i<s.length()+1; i++){
            for (int j=0; j<i; j++) {
                String word = s.substring(j,i);
                if (set.contains(word)) {
                    //dp[i] is true if s.substring(i,j) are in dict && dp[j] is true
                    dp[i] = dp[i] || dp[j]; // from [0,j-1] is valid(dp[j]) and [j,i] is valid
                }
            }
        }       
        
        return dp[s.length()];
    }
}

//Method 2: recursion
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] memo = new boolean[s.length()]; //memo[i]: whether s.substring(i) has valid word break
        boolean[] visited = new boolean[s.length()]; //visited[i]:whether result for s.substring has been recorded in memo
        return dfs(s, 0, new HashSet<>(wordDict),visited, memo);
    }
    
    //whether s.substring(start) has valid word break
    public boolean dfs(String s, int start, Set<String> set, boolean[] visited, boolean[] memo){
        if (start == s.length()) return true;
        if (visited[start]) return memo[start];
        
        visited[start] = true;
        for (int end = start+1; end<=s.length(); end++){
            String word = s.substring(start, end);
            if (set.contains(word) && dfs(s, end, set, visited, memo)){
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false; 
    }
}