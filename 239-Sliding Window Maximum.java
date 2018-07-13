/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?


Author: Mindy927 */



class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length==0) return nums; 
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b-a)); //max heap
        int[] result = new int[n-k+1];
        
        //Inisialize 
        for (int i=0; i<k; i++){
            pq.offer(nums[i]);
        } 
        
        for(int i=0; i<n-k+1; i++){
            result[i] = pq.peek();
            pq.remove(nums[i]);
            if (i!= n-k) pq.offer(nums[i+k]); //Dont offer for last element
        }
        
        return result;
    }
}