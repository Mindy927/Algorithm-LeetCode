/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

Author: Mindy927 */


/*
Method 1: Sort O(nlgk ) / O(1)

Method 2:  Heap 
min Heap with pq.size ()==k 
   return pq.peek()

Method 3: QuickSelect 
*/

//min heap
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i=0; i<nums.length; i++){
            pq.offer(nums[i]);
            if (pq.size() > k) pq.poll();
        }
        
        return pq.peek();
    }
}


//quick select
