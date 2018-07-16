/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1

Author: Mindy927*/


class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1]; //min # of coins to reach ammount i
        Arrays.fill(dp,Integer.MAX_VALUE); //Integar.MAX_VALUE indicates ammount i is not reachable
        dp[0] = 0;
        
        for (int i=0; i<amount+1; i++){
            for (int c:coins){
                if (i-c >= 0 && dp[i-c]!=Integer.MAX_VALUE) dp[i] = Math.min(dp[i],dp[i-c]+1);//use only when dp[i-c] is reachable

            }
        }
        
        return dp[amount]==Integer.MAX_VALUE? -1:dp[amount];
    }
}