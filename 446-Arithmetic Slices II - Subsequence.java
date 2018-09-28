/*
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]

Author: Mindy927*/


class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        //Map[i]: map<diff:cnt>, number of sequence(may contain length 2) with difference diff ends at A[i]
        Map<Integer, Integer>[] maps = new Map[A.length];
        
        for (int i=0; i<A.length; i++){
            maps[i] = new HashMap<>();
            for (int j=0; j<i; j++){
                long lDiff = (long) A[i] - A[j]; //deal with overflow
                if (lDiff > Integer.MAX_VALUE || lDiff <= Integer.MIN_VALUE) continue;
                int diff = (int) lDiff;
                
                int prevCnt = maps[j].containsKey(diff)? maps[j].get(diff):0;
                res += prevCnt; //even if prevCnt may contains sequence of lengnth 2, add ith elment will make it with length >= 3
                int curCnt = maps[i].containsKey(diff)? maps[i].get(diff):0;
                //prevCnt: append ith element to make new sequence
                //curCnt: sequence found previously for diffenetn j with smae diff 
                // + 1 , new sequence (nums[j], nums[i])
                maps[i].put(diff, prevCnt + curCnt + 1);
            }
        }
        
        return res;
    }
}