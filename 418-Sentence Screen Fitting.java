/*
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
Example 1:

Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output: 
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
Example 2:

Input:
rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

Output: 
2

Explanation:
a-bcd- 
e-a---
bcd-e-

The character '-' signifies an empty space on the screen.
Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output: 
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

Author: Mindy927*/

//cnt record number of chars has been fitted in the screen => cnt%len is next char to be fit
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length();
        int cnt = 0; //next possible position for word
        
        for (int i=0; i<rows; i++){
            cnt += cols; //we could fit at most cols chars in each row
            if (s.charAt( cnt % len ) == ' ') cnt++;
            else {
                // cnt % len: index of next char we are going to fit in the sentence
                while (cnt>0 && s.charAt( (cnt-1) % len ) != ' ') cnt--; 
            }
        }
        
        return cnt / len;
    }
}