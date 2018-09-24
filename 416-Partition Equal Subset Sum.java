/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

Author: Mindy927*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int num:nums) sum += num;
        if ( sum%2 == 1 ) return false;
        
        sum /= 2;
        boolean[][] dp = new boolean[n+1][sum+1]; //dp[i][j]:whether we can use upto nums[i-1] to make sum j
        
        //initialize
        for (int i=0; i<n+1; i++) {
            dp[i][0] = true; 
        }
        
        //dp[i][j] = dp[i][j] || dp[i-1][j] || dp[i-1][j-nums[i-1]]
        for(int i=1; i<n+1; i++){
            for (int j=1; j<sum+1; j++){
                dp[i][j] = dp[i][j] || dp[i-1][j]; //dont use nums[i-1]
                if (j-nums[i-1] >= 0)
                    dp[i][j] = dp[i][j] || dp[i-1][j-nums[i-1]];//use nums[i-1]
            }
        }
        
        return dp[n][sum];
    }
}