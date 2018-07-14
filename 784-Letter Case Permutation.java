/*
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.

Author: Mindy927*/

class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        dfs(S.toLowerCase(),0, "", result);
        return result;
    }
    
    public void dfs(String S, int start, String temp, List<String> result){
        if (start==S.length()) {
            result.add(temp);
            return;
        }
        
        char c = S.charAt(start);
        if (Character.isDigit(c)) {
            dfs(S, start+1, temp+c, result);
        } else {
            dfs(S, start+1, temp+c, result);
            dfs(S, start+1, temp+ Character.toUpperCase(c), result);
        }
        return;
    }
}