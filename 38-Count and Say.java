/*
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"

Author: Mindy927*/


class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        String s = "1";
        for (int i=1; i<n; i++){
            s = countOnce(s);
        }
        return s;
    }
    
    //count once
    public String countOnce(String s){
        char curChar = s.charAt(0);
        int cnt = 1; //cnt 1 for first char
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<s.length(); i++){
            char c = s.charAt(i);
            if ( c == curChar) cnt++;
            else {
                sb.append(String.valueOf(cnt) + curChar); //convert cnt to String first
                curChar = c;
                cnt = 1;
            }
        }
        sb.append(String.valueOf(cnt) + curChar);
        return sb.toString();
    }
}