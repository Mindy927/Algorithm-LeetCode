/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

Author: Mindy927*/


class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, 0, n, "", result);
        return result;
    }
    
    public void dfs(int left, int right, int n, String temp, List<String> result){
        if (left == right && left == n) {
            result.add(temp);
            return;
        }
        
        if (left < n) dfs(left+1, right, n, temp+"(", result);
        if (right < left) dfs(left, right+1, n, temp+")", result);
    }
}