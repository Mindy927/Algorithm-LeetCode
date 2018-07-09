/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Author:Mindy927 */



/*
Always remove peak digit : digit larger than its next digit
— push to stack when increase
— pop from stack when stack.peek() >= cur digit 
— trim leading 0s
*/
class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<num.length(); i++){
            char cur = num.charAt(i);
            while (k>0 && !stack.isEmpty() && stack.peek() > cur){
                stack.pop();
                k--;
            }
            stack.push(cur);
        }
        
        while(k>0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        String str = sb.reverse().toString();
        int i=0;
        while (i< str.length() && str.charAt(i) == '0') i++; //trim leading 0s
        return i==str.length()? "0":str.substring(i,str.length());
        
    }
}