
/*
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Author: Mindy927*/

/*
Method 1: DP, can use for arbitrary number, Method 2 is better for positive number
dp[i][j]: min subArray sum when splitting first i elements to j parts
dp[i][j] = min ( max (dp[k][j-1], nums[k+1]+... nums[i])) 
                                  last part

*/
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n+1][m+1]; //dp[i][j]: min subArray sum for splitting nums[0]..nums[i-1] to j parts
        int[] sum  = new int[n+1]; //sum[i]: nums[0] + ... nums[i-1]
        
        for (int i=1; i<n+1; i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
         
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[0][0] = 0;
        
        for (int i=1; i<n+1; i++){
            for (int j=1; j<m+1; j++){
                for (int k=0; k<i; k++){ //sum[i] - sum[k]  = nums[k] + ... nums[i-1]
                    dp[i][j] = Math.min(Math.max(dp[k][j-1], sum[i]-sum[k]), dp[i][j]);
                }
            }
        }
        
        return dp[n][m];
    }
}

/*
Method 2: Binary Search, search all possible sums for subarrays
(1)— left: max element in the array
    — right: sum
(2) Each time verify if we can split into <= m subArrays with largest sum being mid( (left+right)/2)
*/

class Solution {
    public int splitArray(int[] nums, int m) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num:nums){
            max = Math.max(num, max);
            sum += num;
        }
        
        int left = max, right = sum;
        while (left + 1 < right){
            int mid = left + (right - left)/2;
            if (valid(nums, m, mid)) right = mid;
            else left = mid;
        }
        
        return valid(nums, m, left)? left:right;
    }
    
    public boolean valid(int[] nums, int m, int maxSum){
        int cnt = 1, curSum = nums[0];
        for (int i=1; i<nums.length; i++){
            if (curSum + nums[i] <= maxSum) curSum += nums[i];
            else {
                cnt++;
                curSum = nums[i]; //new sub array
            }
        }
        
        return cnt <= m ;
    } 
}