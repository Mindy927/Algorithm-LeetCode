
/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0

Author
*/


/*

Key Idea:
a bit is 0 in any number will result in 0 in the answer's corresponding 
=> If m!=n,  must be at least one 0 at last bit, the AND result is 0 for last bit

The final result will always be like  XXXX00000
==> Find prefix of two numbers

*/

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while (m!=n){
            m >>= 1;
            n >>= 1;
            shift ++;
        }
        return m << shift;
    }
}