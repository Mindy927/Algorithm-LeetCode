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

//Method 2: O(N) time O(1) space 
//scan from right to left, cnt++ for "#", cnt-- otherwise, append to sb when cnt = 0
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return helper(S).equals(helper(T));
    }
    
    public String helper(String s){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i=s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if (cnt == 0 & c!='#') sb.append(c);
            else if (c == '#') cnt++;
            else cnt--;
        }
        return sb.toString();
    }
}