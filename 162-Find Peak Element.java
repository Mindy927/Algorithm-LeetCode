/*
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.


Author:Mindy927*/

class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        //at least three numbers when executing while loop: left, left+1,right => mid will not be 0 or nums.length-1
        while ( left+1 < right ){
            int mid = left + (right - left)/2;
            boolean cond1 = mid == 0 || nums[mid] > nums[mid-1];
            boolean cond2 = mid == nums.length-1 || nums[mid] > nums[mid+1]; 
            
            if (cond1 && cond2) return mid; //mid is peak
            else if (cond1) left = mid; //when cond1, there is number < mid on the left, move left to mid
            else right = mid;
        } 
        
        return nums[left] > nums[right]? left:right;
    }
}