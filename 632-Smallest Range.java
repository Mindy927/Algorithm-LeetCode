/*

You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.

Author: Mindy927*/


class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        //heap of indices based on value of number
        int n = nums.size();
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>( (a,b) ->(nums.get(a[0]).get(a[1]) - nums.get(b[0]).get(b[1])) ); 
        //no need maxHeap, a state variabe max is enough, update max when adding a new item to minHeap   
        
        //1. Inisialize: offer first element of each row to heap
        for (int i=0; i<nums.size(); i++){ 
            minHeap.offer(new int[]{i,0});
            max = Math.max(max, nums.get(i).get(0));
        }
        
        int curMin = nums.get(minHeap.peek()[0]).get(minHeap.peek()[1]);
        int[] res = new int[]{curMin, max};
        int range = max - curMin;
        
        //2. Merge sort
        while (minHeap.size() == n){
            int[] pos = minHeap.poll();
            if (pos[1]+1 == nums.get(pos[0]).size()) break;  
            int[] next = new int[]{pos[0], pos[1]+1}; //similiar to merge sort, move to next element in the same row
            minHeap.offer(next);
            
            curMin = nums.get(minHeap.peek()[0]).get(minHeap.peek()[1]);
            max = Math.max(max, nums.get(pos[0]).get(pos[1]+1));
            
            if(max - curMin < range){
                res[0] = curMin;
                res[1] = max;
                range = max - curMin;
            }
        }
        
        
        return res;
        
    }
}