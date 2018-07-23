/*
A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

Author: Mindy927*/



class Solution {
    public String toGoatLatin(String S) {
        String[] strs = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<strs.length; i++){
            sb.append(helper(strs[i],i)+" ");
        }
        return sb.toString().trim(); 
    }
    
    public String helper(String s, int index){ //deal a single word
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        
        if (set.contains(s.charAt(0))) sb.append(s + "ma");  
        else sb.append(s.substring(1) + s.charAt(0) + "ma");
        for (int i=0; i<index+1; i++) sb.append("a");
        
        return sb.toString();
    }
}