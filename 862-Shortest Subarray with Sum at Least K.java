/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3

Author: Mindy927 */


/*
Deque  
(1) Sum Array, Sum [i] keeps sum of nums[0] to nums[i-1]
(2) Deque of index i such that sum[i] is increasing (MonoQueue)
Only when sum[i] is increasing it can be next possible 

Consider x < y with sum[x] <= sum[y] + K
if x1 < x2 and sum[x1] >= sum [x2] (decrease), x2 is a better solution than x1 since sum[y] - sum [x2] >= sum[y] - sum[x1]
poll x1 and maintain deque increase
*/

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] sum = new int[n+1];
        for(int i=1; i<n+1; i++){
            sum[i] = sum[i-1] + A[i-1];
        }
        
        Deque<Integer> q = new ArrayDeque<>(); //deque of indices
        int min = n+1; 
        for (int i=0; i < n+1; i++){
            while (!q.isEmpty() && sum[i] - sum[q.peekFirst()]>=K) 
                min = Math.min(min, i - q.pollFirst());
            
            while (!q.isEmpty() && sum[q.peekLast()]>= sum[i])  //Maintain MonoQueue
                q.pollLast();
            q.addLast(i);
        }
        
        return min==n+1? -1:min;
    }
}