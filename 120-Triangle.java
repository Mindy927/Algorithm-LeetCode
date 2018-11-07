/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Author: Mindy927*/


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        int n = triangle.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = triangle.get(0).get(0);
        for (int i=1; i<n; i++){
            int[] temp = dp.clone();
            for (int j=0; j<=i; j++){
                int left = j-1>=0? temp[j-1]:Integer.MAX_VALUE;
                dp[j] = Math.min(left, temp[j]) + triangle.get(i).get(j);
            }    
        }
        
        int res = Integer.MAX_VALUE;
        for (int sum:dp) res = Math.min(res, sum);
        return res;
    }
}