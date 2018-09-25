/*Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

Author: Mindy927 */



class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        
        int num = 0;
        String result = "";
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)) num = num*10 + c -'0';
            else if (c == '['){ //push to stack when '['   
                numStack.push(num);
                num = 0;
                strStack.push(result);
                result = "";
            }else if (c == ']'){ // result = strStack.pop() + count * result
                int count = numStack.pop();
                StringBuilder sb = new StringBuilder();
                sb.append(strStack.pop());
                while (count > 0) {
                    sb.append(result);
                    count--;
                }
                result = sb.toString();
            }else {
                result = result + s.charAt(i);
            }
        }
        
        return result;
    }
}


//version 2
class Solution {
    public String decodeString(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> cntStack = new Stack<>();
        
        int cnt = 0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                cnt = cnt * 10 + c - '0';
            }
            
            // push cnt to cntStack when reach "["
            else if (c == '[') {
                strStack.push(c + "");
                cntStack.push(cnt);
                cnt = 0;
            }
            
            //pop all str until "[", repeat cntStack.pop() times and push back to StrStack
            else if (c == ']'){ 
                StringBuilder sb = new StringBuilder();
                while (!strStack.isEmpty() && !strStack.peek().equals("[")){
                    sb.insert(0, strStack.pop());
                }
                if (!strStack.isEmpty()) strStack.pop(); //pop "["
                int curCnt = cntStack.pop();
                String curStr = sb.toString();
                sb = new StringBuilder();
                for (int j=0; j<curCnt; j++) sb.append(curStr);
                strStack.push(sb.toString());   
            } 
            
            else { //char
                strStack.push(c + "");
            }
        }
        
        String res = "";
        while (!strStack.isEmpty()) res = strStack.pop() + res;
        return res;
