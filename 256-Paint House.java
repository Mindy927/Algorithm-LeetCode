/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.

Author: Mindy927*/

class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
       
        //dp[i]: min cost for paiting current house with color i
        int[] dp = new int[]{costs[0][0], costs[0][1], costs[0][2]};
        for (int i=1; i<n; i++){
            int[] temp = new int[3];
            temp[0] = Math.min(dp[1], dp[2]) + costs[i][0]; //previous color could only be 1,2
            temp[1] = Math.min(dp[0], dp[2]) + costs[i][1];
            temp[2] = Math.min(dp[0], dp[1]) + costs[i][2];
            dp = temp;
        }
        
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}