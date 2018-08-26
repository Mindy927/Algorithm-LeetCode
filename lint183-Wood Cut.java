/*
183. Wood Cut
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.

Notice
You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.

Author: Mindy927 
*/

public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        long sum = 0;
        for (int wood:L) sum += wood;
        long left = 1, right = sum/k;
        while (left+1 < right){
            long mid = left + (right - left)/2;
            if (cut(L, mid) < k ) right = mid;
            else left = mid;
        }
        return cut(L,left)>=k? (int)left:(int)right;
    }
    
    public int cut(int[] L, long len){
        int cnt = 0;
        for (int wood:L){
            cnt += (int) wood/len;
        }
        return cnt;
    }
}