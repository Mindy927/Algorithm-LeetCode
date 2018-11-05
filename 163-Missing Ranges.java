/*
Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

Author: Mindy927*/

//use current num as candidate for right boundary, add to result when left + 2 <= right
//use for loop instead of while loop and check for all possible num(similiar to sliding window template)
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        
        long left = (long)lower - 1;  //left boundary, exclusive, convert lower to long to deal with int overflow
        for (int i=0; i<=nums.length; i++){
            long right = i == nums.length ? (long)upper + 1 : nums[i];
            if ( left + 2 == right) res.add(String.valueOf(left+1));
            if ( left + 2 < right ) res.add( (left+1) +  "->" + (right-1));
            left = right;
        }
        
        return res;
    }
}