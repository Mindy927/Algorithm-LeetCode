/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

Author: Mindy927 */
 
//Method 1: dp, O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        int min = 0; //min sum appeared before
        int sum = 0, res = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++){
            sum += nums[i];
            res = Math.max(res, sum - min);
            min = Math.min(min, sum);
        }
        
        return res;
    }
}

//Method 2: Divide and Conquer O(nlgn)

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length-1);
    }
    
    private int maxSubArray(int[] nums, int left, int right) {
        if (left > right) return Integer.MIN_VALUE;
        if (left == right) return nums[left];
        int mid = (left + right) / 2;
        int L = maxSubArray(nums, left, mid-1);  //max sum for subArray[left, mid-1]
        int R = maxSubArray(nums, mid+1, right); //max sum for subArray[mid+1, right]

        //get max sum with left boundary in [left, mid-1], right boundary in [mid+1, right]
        int leftSum = 0;
        int tmp = 0;
        for (int i=mid-1; i>=left; i--) {
            tmp += nums[i];
            if (tmp > leftSum) leftSum = tmp;
        }
        tmp = 0;
        int rightSum = 0;
        for (int i=mid+1; i<=right; i++) {
            tmp += nums[i];
            if (tmp > rightSum) rightSum = tmp;
        }
        return Math.max(Math.max(L, R), leftSum + rightSum + nums[mid]);
    }
}
