/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
Author: Mindy927*/

class Solution {
    public List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();
        if (digits.length()==0) return result;
        result.add("");
        String[] map = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        
        for (int i=0; i<digits.length(); i++){
            String cur = map[digits.charAt(i)-'2'];
            int size = result.size();
            for (int j=0; j<size; j++){
                String prev = result.get(0);
                result.remove(0);
                for (int k=0; k<cur.length(); k++){
                    result.add(prev + cur.charAt(k));
                }
            }
        }
        
        return result;
    }
}