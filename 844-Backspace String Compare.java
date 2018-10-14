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

//Method 1: O(N) time O(1) space 
//Scan backward, cnt++ for '#', cnt-- otherwise
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length()-1, j = T.length()-1;
        while (i>=0 || j>=0){
            //balance S and T by backspace
            int cnt1 = 0; 
            while ( i >= 0 && (cnt1 > 0 || S.charAt(i) == '#')){ //start counter when current char is '#'
                cnt1 += S.charAt(i) == '#'? 1:-1;
                i--;
            }
            int cnt2 = 0;
            while ( j >= 0 && (cnt2 > 0 || T.charAt(j) == '#')){
                cnt2 += T.charAt(j) == '#'? 1:-1;
                j--;
            }
            //compare
            if (i>=0 || j>=0){
                if (i<0 || j<0) return false; //one string reach start while the other doesn't
                if (S.charAt(i) != T.charAt(j)) return false; //both haven't reach start, compare
            }
            if (i>=0) i--; 
            if (j>=0) j--;
        }
        
        return i==-1 && j==-1; //both reach start
    }
}

//Method 2: Stack O(m+n) / O(m+n)
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

/