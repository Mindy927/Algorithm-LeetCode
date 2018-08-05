/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Author: Mindy927*/

//consider case when mid falls at first half or second half
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length-1;
        while (left+1 < right){
            int mid = left + (right - left)/2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]){ 
                if (nums[mid] < target || (nums[right] < nums[left] && target <= nums[right])) left = mid; //corner case when array is not rotated
                else right = mid;
            } else { //nums[mid] < nums[left]
                if (nums[mid] > target || (nums[right] < nums[left] && target >= nums[left])) right = mid;
                else left = mid;
            }
        }
        
        return nums[left]==target? left:nums[right]==target? right:-1;
    }
}