
/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.

Author: Mindy927 */

/*
carry = a & b (bits both are 1)
(1) update a with a^b (bits that differ)
(2)  left shift carry and add carry in next round => update b with carry
*/
class Solution {
    public int getSum(int a, int b) {
        if (b==0) return a;
        if (a==0) return b;
        
        while (b!=0){
            int carry = (a & b);
            a = a ^ b;
            b = (carry << 1);
        }
        
        return a;
    }
}