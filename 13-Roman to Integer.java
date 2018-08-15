/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

Author: Mindy927*/

//consider current char and next char, e.g., for I: -1 if next char is X or V

class Solution {
    public int romanToInt(String s) {
        int cnt = 0;
        int  n = s.length();
        for (int i=0; i<n; i++){
            char c = s.charAt(i);
            char next = i+1 < n? s.charAt(i+1):' ';
            if (c == 'I'){
                if (next == 'V' || next == 'X' ) cnt -= 1;
                else cnt+=1;
            } 
            else if (c == 'V') cnt += 5;
            else if (c == 'X') {
                if (next =='L' || next == 'C') cnt-=10;
                else cnt += 10;
            }
            else if (c == 'L') cnt += 50;
            else if (c == 'C') {
                if (next=='D' || next == 'M') cnt-=100;
                else cnt += 100;
            }
            else if (c == 'D') cnt += 500;
            else if (c == 'M') cnt += 1000;
        }
        
        return cnt;
    }
}