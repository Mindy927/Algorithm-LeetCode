/*

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

Author:Mindy927*/



class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1; 
        if ( (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) sign=-1;
        
        
        long lres= ldivide( Math.abs((long)dividend), Math.abs((long)divisor)); //convert to long before take abs, otherwise cause overflow
        int res = 0;
        if (lres > Integer.MAX_VALUE){
            res = sign==1? Integer.MAX_VALUE:Integer.MIN_VALUE;
        } else{
            res = (int) (sign * lres);
        }
        
        return  res;
    }
    
    public long ldivide(long ldividend, long ldivisor){
        if ( ldivisor > ldividend ) return 0;
        //mul is the max bit of quotient in this round
        long mul = 1;
        long sum = ldivisor;
        
        while (sum + sum < ldividend){ //max sure next sum is still within range before sum
            mul += mul;
            sum += sum;
        }
        
        return mul + ldivide(ldividend-sum, ldivisor);
    }
}