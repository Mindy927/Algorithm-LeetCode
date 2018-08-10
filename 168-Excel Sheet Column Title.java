/*
168. Excel Sheet Column Title
DescriptionHintsSubmissionsDiscussSolution
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"

Author: Mindy927 */
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n!=0){
            n--;     //n-- first in each round before append char, since its 1- 26
            sb.append((char) (n%26 + 'A'));
            n = n/26;
        }
        
        return sb.reverse().toString();
    }
}