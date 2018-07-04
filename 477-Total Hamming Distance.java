
/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.

Author: Mindy927 */


class Solution {
    //For bit i , count number of 1s, m  ==> total hamming distance at bit i is m*(nums.length - m)
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int result = 0;
        //Go through bit by bit
        for (int i=0; i<32; i++){
            int bitCount = 0;
            for (int num:nums){
                bitCount += (num >> i) & 1;
            }
            result += bitCount * (n - bitCount);
        }
        return result;
    }
}