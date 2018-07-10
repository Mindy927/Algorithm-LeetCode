/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Author: Mindy927 */

//Method 1: Stack O(m+n) / O(m+n)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return helper(S).equals(helper(T));    
    }
    
    public String helper(String s){
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == '#' ){
                if (!stack.isEmpty()) stack.pop();
            } 
            else stack.push(c);
        }
        
        return String.valueOf(stack);
    }
}