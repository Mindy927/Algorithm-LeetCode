/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
Author: Mindy927*/

//sort first, and use larger value first, otherwise TLE
//first find sum for each subset, and dfs to fit current num to possible subset
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num:nums) sum += num;
        if (sum % k != 0) return false;
        
        sum /= k;
        int[] subsets = new int[k]; //subset[i]:current sum of ith subset
        Arrays.sort(nums);
        return dfs(nums, nums.length-1, subsets, sum);
    }
    
    //whether we can fit nums[cur] to nums[n-1] to subset, with each subset sum to sum
    public boolean dfs(int[] nums, int cur, int[] subsets, int sum){
        if (cur == -1) return true;
        //we can add nums[cur] to any of the subset
        for (int i=0; i<subsets.length; i++){
            if (subsets[i] + nums[cur] > sum) continue;
            subsets[i] += nums[cur];
            if (dfs(nums, cur-1, subsets, sum)) return true;
            subsets[i] -= nums[cur];
        }
        return false;
    }
}