/*
Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Author :Mindy927 */


/* REMARKS:
word --> 1o1d, 3d 
Cannot be 31 —> num cannot appear at same time */


class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        dfs(word,0,"",false,result);
        return result;
    }
    
    public void dfs(String word, int start, String temp, boolean prevNum, List<String> result){
        if (start == word.length()){
            result.add(temp);
            return;
        }
        
        for(int i=start; i<word.length(); i++){ //change word.substring(start, i+1) to count
            if (i==start) {
                dfs(word,i+1, temp+word.charAt(i),false,result); //use char
            }
            if (!prevNum) { //use count
                 String next = temp + String.valueOf(i-start+1);  
                  dfs(word, i+1, next, true,result);  
            }
        }
    }
}