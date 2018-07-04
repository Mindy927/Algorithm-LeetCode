/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16 
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4 
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0 
Explanation: No such pair of words.
Author: Mind927 */

/*
Creating a unique number for each string（64 bit) and compare number(32bit)
acd 1011           e  00001  
*/

class Solution {
    public int maxProduct(String[] words) {
        int[] pattern = new int[words.length]; 
        
        for (int i=0; i<words.length; i++){
            String str = words[i];
            for (int j=0; j<str.length(); j++){
                pattern[i] |= 1 << (str.charAt(j) - 'a');   
                //pattern[i]'s last bit is set if it contains a, last second bit is set if it contains b, etc
            }    
        }
        
        int max = 0;
        for (int i=0; i<words.length; i++){
            for (int j=0; j<i; j++){
                if ((pattern[i] & pattern[j])==0) max = Math.max(words[i].length() * words[j].length(), max);
            }
        }
        return max;
    }
}