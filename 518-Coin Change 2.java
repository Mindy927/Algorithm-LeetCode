/*
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

Note: You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 

Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
 

Example 3:

Input: amount = 10, coins = [10] 
Output: 1

Author:Mindy927*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1]; //dp[i]: number of ways to get i
        dp[0] = 1;
         
        /*
        if i is outer loop, 5=1+2+1, 5=2+1+2 will both be counted, duplication
        use coins as outer loop to prevent duplication, only 1+2+2 will be counted
        Two dimensional: dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
                        change to 1-D, dp[i][j], dp[i-1][j] can be combined together
        */
        for (int c:coins){ 
            for (int i=1; i<amount+1; i++){
                   if (i-c >= 0) dp[i] += dp[i-c]; //c:coin chosen in last step
            }
        }
        
        return dp[amount];
    }
}


//increasing i then the previous partial result dp[i - coin] is the result that has considered coin already
//decreasing i then the previous partial result dp[i - coin] is the result that has not considered coin yet

increasing i then the previous partial result dp[i - coin] is the result that has considered coin already
decreasing i then the previous partial result dp[i - coin] is the result that has not considered coin yet
/** 
 * @return number of ways to make sum s using repeated coins
 */
public static int coinrep(int[] coins, int s) {
    int[] dp = new int[s + 1]; 
    dp[0] = 1;          
    for (int coin : coins)      
        for (int i = coin; i <= s; i++)         
            dp[i] += dp[i - coin];                                  
    return dp[s];
}                                       
                                            
/**
 * @return number of ways to make sum s using non-repeated coins
 */
public static int coinnonrep(int[] coins, int s) {
    int[] dp = new int[s + 1];
    dp[0] = 1;  
    for (int coin : coins)
        for (int i = s; i >= coin; i--)
            dp[i] += dp[i - coin];              
    return dp[s];                                                   
} 