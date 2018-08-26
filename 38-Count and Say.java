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
        String res = "1";
        while (n-- > 1){
            res = helper(res);
        }
        return res;
    }
    
    //count once
    public String helper(String str){
        char[] chars = str.toCharArray();
    
        int cnt = 1, n = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<n; i++){
            if (chars[i] == chars[i-1]) cnt++;
            else {
                sb.append( String.valueOf(cnt) + chars[i-1]);
                cnt = 1;
            }
        }
        sb.append( String.valueOf(cnt) + chars[n-1]);
        return sb.toString();
    }
}