/*
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Author: Mindy927 */


class Solution {
    public int findComplement(int num) {
       int result = 0;
       for(int i=0; i<32; i++){
           if (num>>i == 0) break;
           result |=  (1-((num >> i) & 1)) << i; //set i the bit of result
       }
       return result;
    }
}