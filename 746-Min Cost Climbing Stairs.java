/*
On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
Example 2:
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].

Author: Mindy927*/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int prevMin = 0;
        int curMin = 0; //min cost to reach cur step
        
        for (int c:cost){
            int temp = curMin; 
            curMin = Math.min(prevMin, curMin) + c; //two case: reach from i-1 or i-2
            prevMin = temp;
        }
        
        return Math.min(prevMin,curMin);
    }
}