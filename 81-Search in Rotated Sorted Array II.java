/*

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?

Author:Mindy927*/


class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        int left = 0, right = nums.length-1;
        
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target) return true;
            
            //left is sorted[1,2,3,4] or right is unsorted[4,4,1,2]
            if (nums[mid] > nums[left] || nums[mid] > nums[right]){
                //nums[left] <= target since we haven't compare target with nums[left]
                // since left to mid is sorted, we can move right pointer when target falls in this region
                if (nums[left] <= target && target < nums[mid]) right = mid; 
                else left = mid;
            } 
            //right is sorted or left is unsorted
            else if (nums[mid] < nums[right] || nums[mid] < nums[left]){
                if (nums[mid] < target && target <= nums[right]) left = mid;
                else right = mid;
            } 
            //nums[left] = nums[right] = nums[mid]
            else{ 
                left++;
            }
        }
        
        if (nums[left] == target || nums[right] == target) return true;
        return false;
    }
}