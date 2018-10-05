/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

Author: Mindy927 */


class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> set = new HashSet<>(Arrays.asList("+","-","*","/"));
        
        for (String str:tokens){
            if (set.contains(str)) calc(stack, str);
            else stack.push(Integer.parseInt(str)); 
        }
        
        return stack.peek();
    }
    
    public void calc(Stack<Integer> stack, String op){
        int num2 = stack.pop();
        int num1 = stack.pop();
        if (op.equals("+")) stack.push(num1 + num2);
        else if (op.equals("-")) stack.push(num1 - num2);
        else if (op.equals("*")) stack.push(num1 * num2);
        else stack.push(num1 / num2);       
    }
}