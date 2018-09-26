/*
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

Author: Mindy927*/

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0, k = 0;
        int len = 0;//total len of words in a line
        while (i < words.length){
            // at least k spaces required for [0,k] words
            for (k=0, len=0; i+k<words.length && len+words[i+k].length()+k<= maxWidth;k++){
                len += words[i+k].length(); //add words[i+k] to len only when its in bound
            }
            //current line has k words,[0,k-1], hence k-1 gaps
            String temp = words[i]; //start word in current line
            for (int j=1; j<k; j++){
                if (i+k >= words.length) temp += " "; //last line
                else {
                    for (int s=0; s<(maxWidth-len)/(k-1);s++) temp += " ";
                    if (j-1 < (maxWidth-len)%(k-1)) temp += " "; //more space for words on the left     
                }
                temp += words[i+j];
            }
            while (temp.length() < maxWidth) temp += " "; //add extra space for last line
            res.add(temp);
            i += k;
        }
        return res;
    }
}