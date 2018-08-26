/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5

Author:Mindy927 */
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i=0; i<=s.length(); i++){ 
            char c = i<s.length()? s.charAt(i):'+'; //deal with end of string as sign '+'
            if (c == ' ') continue;
            if (Character.isDigit(c)) num = num * 10 + (c - '0');
            else {
                if (sign == '*') stack.push(stack.pop() * num);
                else if (sign == '/') stack.push(stack.pop()/num);
                else if (sign == '+') stack.push(num);
                else if (sign == '-') stack.push(-num);
                sign = c;
                num = 0;
            }
        }
        
        int res = 0;
        while (!stack.isEmpty()){
            res += stack.pop();
        }
        
        return res;
    }
}