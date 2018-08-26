/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

Author: Mindy927*/


class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>(); //string:all possible word break
        Set<String> set = new HashSet<>();
        for (String word:wordDict) set.add(word);
        
        return dfs(s, set, map);
    }
    
    public List<String> dfs(String s, Set<String> set, Map<String, List<String>> map){
        if (map.containsKey(s)) return map.get(s);
        
        List<String> result = new ArrayList<>();
        if (s.length() == 0){//reach end of string, return "" for previous strings to append themselves
            result.add("");
            return result;
        }
        
        for (int i=0; i<s.length(); i++){ 
            String word = s.substring(0,i+1);
            if (set.contains(word)){ //dict contains word s[i,j]
                List<String> strs = dfs(s.substring(i+1), set, map);
                for (String str:strs){
                    if (str.equals("")) result.add(word); //last word
                    else result.add(word + " " + str);
                }
            }
            
        }
        map.put(s, result);
        return result;
    }
    
}