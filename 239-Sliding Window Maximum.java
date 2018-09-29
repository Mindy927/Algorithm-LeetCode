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


//Method 1: max heap O(nlgk)
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


//Method 2: deque of indices
/* 
From beginning, remove items with index < i-k+1
From end, remove items k with nums[k] < nums[i], cause we have i alreay, k will not be max anymore
In this way, deque with number DECREASING
*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        Deque<Integer> dq = new LinkedList<>(); //deque of indices, val decreasing
        int[] res = new int[nums.length - k + 1];
        
        for (int i=0; i<nums.length; i++){
            //indices in dq is smaller than i, if nums[prev] < nums[i], they wont be max, i.e, useless, pop from dq
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){
                dq.pollLast();
            }
            //remove indices out of boundary
            if (!dq.isEmpty() && i-dq.peekFirst()+1 >k) dq.pollFirst();
            dq.offer(i);
            if (i>=k-1) res[i-k+1] = nums[dq.peekFirst()];
        }
        
        return res;
    }
}
