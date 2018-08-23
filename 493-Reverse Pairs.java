/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

Author:Mindy927*/


/*
Key idea: sort elements and do binary search instead of linear scan
merge sort, divide array into two parts cnt(left, right) = cnt(left, mid) + cnt(mid+1, right) + C, 
C is number of reverse pairs (i,j) such that i in [left, mid], j in [mid+1, right] , calculated using two pointers
*/

class Solution {
    public int reversePairs(int[] nums) {
        return merge(nums, 0, nums.length-1);
    }
    
    //merge sort from start to end and return number of reverse pairs in [start, end]
    public int merge(int[] nums, int start, int end){
        if (start >= end) return 0;
        int mid = start + (end - start)/2;
        int cnt = merge(nums, start, mid) + merge(nums, mid+1, end);
        
        int[] sorted = new int[end - start + 1];
        int i= start, j= mid+1; //pointers for reverse pair
        int idx = 0, k = mid+1; //pointers for merge sort
        
        while ( i<= mid ){
            //cnt number of reverse pair(i,j) with i in range[start, mid], j in range[mid+1, end]
            while (j<=end && nums[i]/2.0 > nums[j])  j++;//number of j that cound form reverse pair with current num i
            cnt += j - (mid+1);
            
            //merge sort
            while (k<=end && nums[k] <= nums[i]){ //number of numbers in right half < nums[i]
                sorted[idx++] = nums[k++];
            }
            
            sorted[idx++] = nums[i++]; //increase i
        }
        
        while ( k<=end ) sorted[idx++] = nums[k++];
        
        //copy sorted array to nums
        for (int t=start; t<=end; t++) nums[t] = sorted[t-start];
        
        return cnt;
    }
}