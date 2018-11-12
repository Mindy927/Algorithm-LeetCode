/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

Author: Mindy927*/

class Solution {
    public int rob(int[] nums) {
        //either rob house 0 to n-2 or 1 to n-1, cause we cannot rob 0 and n-1 at same time
        int n = nums.length;
        if (n==1) return nums[0];
        return Math.max(helper(nums,0,n-2), helper(nums, 1, n-1));
    }
    
    //max profit for robbing house from low to high
    public int helper(int[] nums, int low, int high){
        if (nums.length==0 || high < low) return 0;
        int rob = nums[low];
        int notRob = 0;
        for(int i=low+1; i<=high; i++){
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(temp, notRob);
        }
        return Math.max(rob, notRob);
    }
}