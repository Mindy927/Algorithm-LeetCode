/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Author: Mindy927*/

//binary search, helper function to find left boundary and right boundary when nums[mid] = target
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int[] result = new int[]{-1,-1};
        if (nums.length == 0) return result;
        
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target){
                result[0] = findLeft(nums, left, mid, target);
                result[1] = findRight(nums, mid, right, target);
                return result;
            } else if (nums[mid] < target) left = mid;
            else right = mid;
        }
        
        if (nums[left] == target && nums[right] != target) {
            result[0] = left; result[1] = left;
        }
        if (nums[right] == target && nums[left] != target){
            result[0] = right; result[1] = right;
        }
        
        if (nums[left] == target && nums[right] == target){
            result[0] = left; result[1] = right;
        }
        return result;
    }
    
    public int findLeft(int[] nums, int left, int right, int target){ //find first nums[left] == target
        while (left+1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] < target) left = mid;
            else right = mid;
        }
        
        return nums[left] == target? left:right;
    }
    
     public int findRight(int[] nums, int left, int right,int target){ //find first nums[right] == target
        while (left+1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] > target) right = mid;
            else left = mid;
        }
        
        return nums[right] == target? right:left;
    }
    
}