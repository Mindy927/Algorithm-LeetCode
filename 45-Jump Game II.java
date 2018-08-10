/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Author: Mindy927 */


//Method 1: Greedy. 

class Solution {
    //Iterate nums, curMax is the farthest point that all points in [0, curEnd] can reach. When i reaches curEnd, set curEnd to curMax, cnt++
    public int jump(int[] nums) {
        int cnt = 0;
        int curMax = 0;
        int curEnd = 0;
        for (int i=0; i<nums.length-1; i++){ //nums.length-1, since we dont need to jump at last pos
            curMax = Math.max(curMax, i+nums[i]);
            if (i == curEnd){
                curEnd = curMax;
                cnt++;
            }
        }
        
        return cnt;
    }
}


//Method 2: DP, Beats 2%
class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length && j<=i+nums[i]; j++){ //can jump to nums[j] from nums[i]
                dp[j] = Math.min(dp[i]+1, dp[j]);
            }
        }
        
        
        return dp[nums.length-1];
    }
}