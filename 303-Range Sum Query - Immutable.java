/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Author: Mindy927 */

class NumArray {
    int[] sum;
    public NumArray(int[] nums) {
        sum = new int[nums.length+1]; //sum[i] = sum(nums[0],...nums[i-1])
        for (int i=1; i<nums.length+1; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */