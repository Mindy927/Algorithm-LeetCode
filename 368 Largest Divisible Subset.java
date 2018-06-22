/*Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.
Author: Mindy927 */

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        if (nums.length == 0) return result;
        int[][] dp = new int[n][2];
             
        // dp[i][0]: max numbers of subsets end with nums[i] 
        // dp[i][1]: index of previous num where dp[i][0] get from
        for (int i=0; i<n; i++){
            dp[i][0] = 1;
            dp[i][1] = -1;
            for (int j=i-1; j>=0; j--){
                if (nums[i] % nums[j] == 0 && dp[j][0] + 1 > dp[i][0]) {
                    dp[i][0] = dp[j][0]+1;
                    dp[i][1] = j;
                }
            }
        }
        
        //find max number of subsets 
        int index = 0, max = 0;
        for (int i=0; i<n; i++){
            if ( dp[i][0] > max ){
                max = dp[i][0];
                index = i;
            }
        }
        
        //Build result list
        while (index != -1){
            result.add(nums[index]);
            index = dp[index][1];
        }
        
        return result;
    }
}