/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
Author: Mindy927*/


//bfs
class Solution {
    public List<String> letterCombinations(String digits) {
        Queue<String> q = new LinkedList<>();
        String[] map = new String[]{"", "", "abc", "def", "ghi","jkl", "mno", "pqrs","tuv","wxyz"};
        
        q.offer("");
        for (int i=0; i<digits.length(); i++){
            String cur = map[digits.charAt(i) - '0'];
            int size = q.size();
            for (int s=0; s<size; s++){
                String prev = q.poll();
                for (char c:cur.toCharArray()){
                    q.offer(prev + c);
                }
            }
        }
        
        if (q.peek().equals("")) return new ArrayList<>(); //corner case ""
        return new ArrayList<>(q);
        
    }
}