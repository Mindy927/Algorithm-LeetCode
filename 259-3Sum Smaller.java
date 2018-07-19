/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?

Author: Mindy927*/

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) return 0;
        //iterate i, move j when < target, move k when > target, 
        
        Arrays.sort(nums);
        int cnt = 0;
        for (int i=0; i<nums.length-2; i++){
            int j = i+1, k = nums.length-1;
            while (j<k){
                if (nums[i] + nums[j] + nums[k] < target) {
                    cnt+= k-j; //for each j, if current k satisfy, k-1, k-2 statify as well, continue search for j+1   
                    j++;
                } else k--;
            }
        }
        
        return cnt;
    }
}