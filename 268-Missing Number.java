/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

Author: Mindy927 */

//Method 1: start with total sum
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (0 + n ) * (n+1)/2; //0 to n fits in [0,n-1]
        
        for (int i=0; i<nums.length; i++){
            sum -= nums[i];
        }
        return sum;
    }
}

//Method 2: bit manipulation
class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        
        // for each number k, they will be a pait, one k at index, one k at nums[index], XOR is zero
        for (int i=0; i<nums.length; i++){
            missing = missing ^ i ^ nums[i]; 
        }
        
        return missing;
    }
}