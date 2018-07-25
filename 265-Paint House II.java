/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 

 Author: Mindy927*/


class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 ) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k]; //dp[i][j]:min cost of painting house i with color j
        
        for (int j=0; j<k; j++){
            dp[0][j] = costs[0][j];
        }
        
        for (int i=1; i<n; i++){
            for (int j=0; j<k; j++){
                int prevCost = Integer.MAX_VALUE; 
                for (int c = 0; c<k; c++){
                    if (c==j) continue; //previous house cannot be color j
                    prevCost = Math.min(dp[i-1][c], prevCost);
                }
                dp[i][j] = prevCost + costs[i][j];
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int last:dp[n-1]) min = Math.min(last, min); //min costs to finish painting last house
        return min;
    }
}