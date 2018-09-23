/*
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

Author: Mindy927*/

//tow pointers
class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int)Math.sqrt(c)+1;
        while (a <=b){
            int temp = a * a + b * b;
            if (temp == c) return true;
            if ( temp < c) a++;
            else b--;
        }
        return false;
    }
}