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
//time: O(nlgn) / space:O(k)
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
//time: O(N) best case, O(N^2) worse case,  space: O(1)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length-1, target = nums.length-k;//k-th largest is at index n-k
        
        //binary search
        while (left < right){
            int cur = quickSort(nums, left, right);
            if (cur == target)  return nums[target];
            else if (cur < target) left = cur + 1;
            else right = cur - 1; //left to cur-1 are elements < nums[cur], continue sort these numbers
        }
        return nums[target];
    }
    
    //quick sort, return index of pivot, 0 to index-1 are numbers < pivot
    public int quickSort(int[] nums, int left, int right){
        int pivot = nums[right]; //pick last element as pivot
        int i=left;
        //right is next position to swap when num>pivot, its value is not verified, has to check when i==right
        while (i <= right){  
            if (nums[i] < pivot) {
                swap(nums, left, i);
                if (i==left) i++;
                left++;
            }else if (nums[i]> pivot) swap(nums, right--,i);
            else i++;
        }
        return left;
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
