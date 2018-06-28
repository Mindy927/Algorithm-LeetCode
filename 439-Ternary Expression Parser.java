/*Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.

Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"


Author:Mindy927 */


class Solution {
    public String parseTernary(String expression) { 
        char[] chars = expression.toCharArray();
        return dfs(chars, 0, chars.length-1) + "";
    }
    
    public char dfs(char[] chars, int start, int end){ // [start, end] 
        if (start==end) return chars[start]; 
        int count = 0;
        boolean flag = chars[start]=='T';
        start++; 
        
        int i = start;
        for (; i<=end; i++){ //start from first '?'
            if (chars[i] == '?') count++;
            if (chars[i] == ':') count--;
            if (count == 0) break;  
        }
        //Optimization: put DFS call at the end, only make DFS call based on flag
        return flag? dfs(chars,start+1,i-1):dfs(chars,i+1,end);
    }
}