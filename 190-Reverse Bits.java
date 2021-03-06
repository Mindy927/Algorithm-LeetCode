/*
Reverse bits of a given 32 bits unsigned integer.

Example:

Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
return 964176192 represented in binary as 00111001011110000010100101000000.

Author: Mindy927*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i<32; i++){
            int bit = (n >> i) & 1;
            result += bit << (31-i);
        }
        
        return result;
    }
}

/*
Follow Up
If this function is called many times, how would you optimize it?
Use cache to store computed value