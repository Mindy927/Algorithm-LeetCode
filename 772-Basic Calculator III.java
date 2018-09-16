/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

Author:Mindy927*/

/*
two stack, one store numbers, the other store operators(including parentheses)
use helper func pre-req to check whether we need to pop two elements from nums, calculated and push back before pushing current operator
*/

class Solution {
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == ' ') continue;
            
            if (Character.isDigit(c)){
                int num = c - '0';
                while ( i+1 < s.length() && Character.isDigit(s.charAt(i+1))){
                    num = num * 10 + s.charAt(i+1) - '0';
                    i++;
                }
                nums.push(num);
            }
            
            if (c == '(') operators.push(c);
            if (c == ')') {
                while (!operators.isEmpty() && operators.peek()!='('){
                    nums.push( calculate (operators.pop(), nums.pop(), nums.pop()));
                }
                operators.pop(); //pop (
            }
            if ( c == '+' || c == '-' || c == '*' || c == '/'){
                while (!operators.isEmpty() && preReq(operators.peek(), c)){ //deal with operator.peek() before push c
                    nums.push( calculate (operators.pop(), nums.pop(), nums.pop()));
                }
                operators.push(c);
            }
        }
        
        //calculate final result
        while (!operators.isEmpty()){
            nums.push(calculate (operators.pop(), nums.pop(), nums.pop()));
        }
        
        return nums.pop();
    }
    
    public int calculate(char op, int num2, int num1){ //pop latter number first
        if (op == '+') return num1 + num2;
        if (op == '-') return num1 - num2;
        if (op == '*') return num1 * num2;
        if (op == '/') return num1 / num2; 
        return 0;
    }
    
    //return true if prev is pre-requisite of cur operator, 
    //e.g 3*6 + 2, prev is '*', we need to calculate 3*6 and push 18 to stack before push 2 to stack
    //eg 2-1 +2, prev is '-', we need to calculate 2-1 = 1 first, i.e +/- is preReq for +/-
    public boolean preReq(char prev, char cur){
        if (prev == '(' || prev == ')') return false;
        if ( (prev == '+'||prev == '-') && (cur == '*' || cur == '/') ) return false;
        return true;
    }
}