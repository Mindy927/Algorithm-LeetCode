/*

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Author: Mindy927*/

//iterate first number, two pointers for the rest two numbers, be aware of duplicates  O(n^2)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0; i<nums.length-2; i++){
            if (i>0 && nums[i] == nums[i-1]) continue;
            int j = i+1, k = nums.length-1;
            while (j<k){
                int curSum = nums[i] + nums[j] + nums[k];
                if ( curSum == 0) {
                    result.add(Arrays.asList(nums[i],nums[j], nums[k]));
                    while (j<k && nums[j+1]==nums[j]) j++;
                    while (j<k && nums[k-1]==nums[k]) k--;
                    j++; k--;
                }
                else if (curSum < 0 ) j++;
                else k--;
            }
        }
        
        return result;
    }
}

