/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

Author: Mindy927 */


class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        dfs(s, 0, temp, result);
        return result;
    }
    
    public void dfs(String s, int start, List<String> temp, List<List<String>> result){
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i=start; i<s.length(); i++){  //s.substring(start,i+1)
            if (isPalindrome(s.substring(start,i+1))){
                temp.add(s.substring(start,i+1));
                dfs(s,i+1,temp,result);
                temp.remove(temp.size()-1);
            }
        } 
    }
    
    public boolean isPalindrome(String str){
        int i=0, j=str.length()-1;
        while(i<j){
            if (str.charAt(i)!=str.charAt(j)) return false;
            i++; j--;
        }
        return true;  
    }
}