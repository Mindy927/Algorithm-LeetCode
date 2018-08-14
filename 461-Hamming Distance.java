/*

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.

Author:Mind927 */



class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x^y);
    }
}


class Solution {
    public int hammingDistance(int x, int y) {
        int cnt = 0;
        for (int i=0; i<32; i++){
            cnt += (x & (1<<i)) != (y & (1<<i))? 1:0;
        }
        
        return cnt;
    }
}