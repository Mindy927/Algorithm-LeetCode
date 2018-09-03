/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

Author: Mindy927*/
class Solution {
    //find positions to update and update res array based on current round of mul
    public String multiply(String num1, String num2) {
        if (num1.length()==0 || num2.length()==0) return "";
        int[] res = new int[num1.length() + num2.length()];//m+n
        int sum = 0;

        //From least significant digit to most significant digit
        for (int i = num1.length()-1; i>=0; i--){
            for (int j = num2.length()-1; j>=0; j--){
                int pos0 = i+j;
                int pos1 = i+j+1;
                sum = (num1.charAt(i)-'0') * (num2.charAt(j)-'0') + res[pos1] + 10 * res[pos0];
                res[pos1] = sum%10;
                res[pos0] = sum/10; 
            }
        }
        
        StringBuilder sb = new StringBuilder();
        //deal with case 9133*0 = 0 not 0000,dont append until its non-zero
        boolean hasFirst = false;
        for (int i:res) {
            if (i==0 && !hasFirst) continue;
            sb.append(i);
            hasFirst = true;
        }

        return sb.length() == 0 ? "0" : sb.toString();
        
    }
}