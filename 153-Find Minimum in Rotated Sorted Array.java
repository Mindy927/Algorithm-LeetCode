/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0

Author: Mindy927*/


//binary search, always consider relationship for nums[mid], nums[left] first, similiar to #81
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while ( left + 1 < right ){
            int mid = left + (right - left)/2;
            if ( nums[mid] > nums[left]){
                if ( nums[right] > nums[left]) return nums[left]; // nums[left] < nums[mid] < nums[right]
                else left = mid;
            } else {
                right = mid;
            }
        }
        
        return nums[left] < nums[right]? nums[left]:nums[right];
        
    }
}