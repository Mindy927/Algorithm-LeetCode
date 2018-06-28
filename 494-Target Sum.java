/*

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.


Author: Mindy927 */

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int[] count = new int[1];
        dfs(nums, 0, 0, S, count);
        return count[0];
    }
    
    public void dfs(int[] nums, int i,int temp, int target, int[] count){
        if (i==nums.length) {
            if (temp == target) count[0]++;
            return;
        }
        
        dfs(nums, i+1, temp+nums[i], target, count);
        dfs(nums, i+1, temp-nums[i], target, count);
        
    }
}