/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
Author: Mindy927 */

// Method 1: beats 18%
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(nums);
        for (int i=0; i<nums.length-2; i++){
            if (i>0 && nums[i] == nums[i-1]) continue; //remove duplicates
            for (int j=i+1; j<nums.length-1; j++){
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                int start = j+1, end = nums.length-1;
                while (start < end){
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i],nums[j],nums[start], nums[end]));
                        start++; end--;
                        while (start < end && nums[start] == nums[start-1]) start++; //remove duplicates
                        while (start < end && nums[end] == nums[end+1]) end--;
                        
                    }
                    else if (sum < target) start++;
                    else end--;
                }
            }
        }
        
        return res;
    }
}