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
        if (nums.length == 0) return new int[]{-1,-1};
        int left = 0, right = nums.length-1;
        int i = -1, j = -1;
        while (left+1 < right){ //just out of loop when left and right are adjacent, need to verify left/right
            int mid = left + (right - left)/2;
            if (nums[mid] < target) left = mid;
            else if (nums[mid] > target) right = mid;
            else {
                i = findBoundary(nums, left, mid, target,true); //find left boundary
                j = findBoundary(nums, mid, right, target, false); //find right boundary
                return new int[]{i,j};
            }
        }
        
        i = nums[left] == target? left:nums[right] == target? right:i;
        j = nums[right] == target? right:nums[left] == target? left:j;
        return new int[]{i,j};
    }
    
    //find left/right boundary
    public int findBoundary(int[] nums, int left, int right, int target, boolean isLeft){
        while (left+1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] < target) left = mid;
            else if (nums[mid] > target) right = mid;
            else {
                if (isLeft) right = mid;
                else left = mid;
            }
        }         
        if (isLeft) return nums[left] == target? left:right;
        else return nums[right] == target? right:left;
    }
}