

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.

Author:Mindy927*/

//version 1: two stacks, from basic calc III
class Solution {
    public int calculate(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        
        s = "(" + s + ")"; //nums will have only 1 result in this case
        for (int i=0; i<s.length(); i++){
            //if (!nums.isEmpty()) System.out.println(nums.peek());
            char c = s.charAt(i);
            if ( c == ' ') continue;
            if (Character.isDigit(c)){ //get the whole num
                int num = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i++) - '0';
                }
                nums.push(num);
                i--; //revert to last digit
            }
            else if ( c == '(') ops.push(c);
            else if ( c == '+' || c == '-' || c == ')'){ //need to calculate +, - ops appeared before
                while (!ops.isEmpty() && (ops.peek() == '+' || ops.peek() == '-')){
                    int num2 = nums.pop();
                    int num1 = nums.pop();
                    char op = ops.pop();
                    if (op == '+') nums.push(num1 + num2);
                    else nums.push(num1 - num2);
                }
                if (c == ')'&&!ops.isEmpty() && ops.peek() == '(') ops.pop(); //pop '(' only for ')'
                if (c == '+' || c == '-') ops.push(c); //push only '+', '-'
            }
        }
        
        return nums.peek();
    }
}