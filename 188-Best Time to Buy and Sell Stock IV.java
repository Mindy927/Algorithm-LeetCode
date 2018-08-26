/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Author: Mindy927 */


//Updated version with local optimization
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
    
        if (k >= n/2){
            int max = 0;
            for (int i=1; i<n; i++){
                if (prices[i] > prices[i-1]) max += prices[i] - prices[i-1];
            }
            return max;
        } 
        
        //create dp table after handled case above
        int[][] dp = new int[k+1][n]; //dp[i][j] : max profix with ith transaction at day j
        for (int i=1; i<k+1; i++){
            int maxHold = -prices[0]; //cur max profix when holding 1 share
            for (int j=1; j<n; j++){
                dp[i][j] = Math.max(dp[i][j-1], maxHold + prices[j]);
                maxHold = Math.max(maxHold, dp[i-1][j-1] - prices[j]); //we can buy 1 share on day j
            }
        }
        
        return dp[k][n-1];
    }
}

//Memory Limit Exceed Version
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[k+1][n]; //dp[i][j] : max profix with ith transaction at day j
        
        if (k >= n/2){
            int max = 0;
            for (int i=1; i<n; i++){
                if (prices[i] > prices[i-1]) max += prices[i] - prices[i-1];
            }
            return max;
        } 
        
        for (int i=1; i<k+1; i++){
            for (int j=1; j<n; j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i][j]) ;//max same as day j-1
                dp[i][j] = Math.max(-prices[0] + prices[j], dp[i][j]) ; //last transaction made on day 0
                for (int m=1; m<j; m++){ //last transaction made at day m
                    dp[i][j] = Math.max(dp[i-1][m-1] - prices[m] + prices[j], dp[i][j]);
                }
            }
        }
        
        return dp[k][n-1];
    }
}