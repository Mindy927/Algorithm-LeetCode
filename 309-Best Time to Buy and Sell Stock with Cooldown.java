/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Author: Mindy927 */

/*
two dp table
1. buy[]:  max profit when hold 1 share at day i
   sell[]:  max profit when hold 0 share at day i
2. two cases for hold 1 share at day i
a) sell at day i-2 and buy at day i
b) hold 1 share at day i-1
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length ==0) return 0;
        int n = prices.length;
        int[] buy = new int[n]; //max profit when hold 1 share at day i
        int[] sell = new int[n]; //max profit when hold 0 share at day i
        
        buy[0] = - prices[0];
        for (int i=1; i<n; i++){
            int prevSell = i>2? sell[i-2]:0;
            buy[i] = Math.max( (prevSell - prices[i]),buy[i-1]);
            sell[i] = Math.max( buy[i-1] + prices[i], sell[i-1]);
        }
        
        return sell[n-1];
    }
}