/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).

Author: Mindy927 */
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n-k+1]; //sum[i]: sum of subArray with size k starts from element i
        int[] left = new int[n-k+1]; //left[i]: start index of largest subArray sum with start index <=i
        int[] right = new int[n-k+1]; //right[i]: start index of largest subArray sum with start index >=i
        
        //build sum array
        int curSum = 0;
        for (int i=0; i<k; i++){
            curSum += nums[i];
        }
        sum[0] = curSum;

        for (int i=1; i<=n-k; i++){
            sum[i] = sum[i-1] - nums[i-1] + nums[i+k-1]; 
        }
        
        //build left sum 
        left[0] = 0;
        for (int i=1; i<=n-k; i++){
            // if sum[left[i-1]] == sum[i] use left[i-1] since its with smaller lexicographically order
            left[i] = sum[left[i-1]] >= sum[i]? left[i-1]:i; //store index with larget subArray sum
        }
        
        //build right sum
        right[n-k] = n-k;
        for (int i=n-k-1; i>=0; i--){
            // if sum[right[i+1]] == sum[i] use i since its with smaller lexicographically order
            right[i] = sum[right[i+1]] > sum[i]? right[i+1]:i;
        }
        
        //build result
        int finalSum = 0;
        int[] res = new int[3];
        for (int i=k; i<= n-2*k; i++){
            int l = left[i-k];
            int r = right[i+k];
            if (sum[l] + sum[i] + sum[r] > finalSum){
                res[0] = l; res[1] = i; res[2] = r;
                finalSum = sum[l] + sum[i] + sum[r];
            }
        }
        
        return res;
    }
}